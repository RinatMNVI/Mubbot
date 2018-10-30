package answers;

/**
 * Created by mrina on 20.09.2018.
 */
public class Answer {

    private AnswerType answerType;

    public Answer(AnswerType answerType){
        this.answerType = answerType;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }
}
