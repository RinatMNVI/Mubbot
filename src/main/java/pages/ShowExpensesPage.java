package pages;

import config.DataSingl;
import db.User;
import db.domain.Expenses;
import db.domain.MubUser;
import db.domain.Tags;
import db.service.ExpensesService;
import views.buttons.Button;
import views.buttons.InlineButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.InlinePageFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mrina on 12.10.2018.
 */
public class ShowExpensesPage extends DefaultPage {

    private List<Expenses> expList = new ExpensesService().findAllExpenses();

    public ShowExpensesPage(User user){
        this.user = user;

        commands.add(DefaultPages.SHOW_EXPENSES);

        content = showExpCont();

        page = new AnswerPage(new InlinePageFactory(content, addButtonContent(),1));
    }

    public String showExpCont(){
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Tags> userTags = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int all = 0;
        int now = 0;

        stringBuilder.append("РАСХОДЫ\n\n");
        if (user.getTagsList() != null && !user.getTagsList().isEmpty()) {
            user.getTagsList().forEach(x -> userTags.add(x));

            for (Tags t : user.getTagsList()) {
                int sum = 0;
                List<Expenses> expenses = expList.stream()
                        .filter(x -> x.getBuyer().getId() == user.getUser().getId() && x.getTag().getId() == t.getId())
                        .collect(Collectors.toList());
                for (Expenses e : expenses) {
                    sum += e.getCost();
                    if (e.getBuyDate().getMonth().equals(LocalDateTime.now().getMonth()))
                        now += e.getCost();
                }
                map.put(t.getName(), sum);
                all += sum;
            }
        }

        stringBuilder.append("---------------------------------------------\n");
        map.entrySet().forEach(m -> {
            stringBuilder.append(m.getKey() + ": " + m.getValue() + "\n");
        });
        stringBuilder.append("---------------------------------------------\n");

        stringBuilder.append("\nЗа этот месяц: " + now);
        stringBuilder.append("\nВсего потрачено: " + all);

        return stringBuilder.toString();
    }

    @Override
    public DefaultPage setCommand(String command) {
        return this;
    }

    @Override
    public ArrayList<Button> addButtonContent() {
        ArrayList<Button> inlineButtons = new ArrayList<>();
        inlineButtons.add(new InlineButton(DefaultPages.DETALIZATION));

        return inlineButtons;
    }

    public static String detailsExp(User user){
        StringBuilder stringBuilder = new StringBuilder("ДЕТАЛЬНЫЕ РАСХОДЫ\n\n");

        ArrayList<Tags> userTags = new ArrayList<>();
        Map<String, List<Expenses>> map = new HashMap<String, List<Expenses>>();

        Map<Month, Integer> dates = new HashMap<>();

        if (user.getTagsList() != null && !user.getTagsList().isEmpty())
            user.getTagsList().forEach(x -> userTags.add(x));

        List<Expenses> expen = new ExpensesService().findAllExpenses().stream()
                .filter(x -> x.getBuyer().getId() == user.getUser().getId()).collect(Collectors.toList());


        for (Tags t: user.getTagsList()) {
            map.put(t.getName(),expen.stream()
                    .filter(x -> x.getTag().getId() == t.getId())
                    .collect(Collectors.toList()));
        }

        for (Expenses e: expen){
            if (dates.containsKey(e.getBuyDate().getMonth())) {
                    dates.entrySet().stream().filter(x -> x.getKey() == e.getBuyDate().getMonth()).forEach(x -> x.setValue(x.getValue() + e.getCost()));
            } else
                dates.put(e.getBuyDate().getMonth(),e.getCost());
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMMM");

        stringBuilder.append("---------------------------------------------\n");
        map.entrySet().forEach(m -> {
            stringBuilder.append("" + m.getKey() + "\n");
            m.getValue().forEach(expenses -> {
                Instant instant = expenses.getBuyDate().toInstant(ZoneOffset.UTC);
                stringBuilder.append("      " + expenses.getCost() + " руб.     " + sdf1.format(Date.from(instant)) + "    " + expenses.getComment() + "\n");
            });
            stringBuilder.append("\n");
        });
        stringBuilder.append("---------------------------------------------\n\n");

        SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM");
        stringBuilder.append("По месяцам:\n");
        dates.entrySet().stream().forEach(d -> {
            Date date = new Date(0,d.getKey().getValue(),0);
            stringBuilder.append("      " + d.getValue() + " руб.     " + sdf2.format(date) + "\n");
        });
        return stringBuilder.toString();
    }
}
