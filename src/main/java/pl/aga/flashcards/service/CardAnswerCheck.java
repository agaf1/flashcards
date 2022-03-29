package pl.aga.flashcards.service;

import pl.aga.flashcards.repository.Box;
import pl.aga.flashcards.repository.Card;

import java.util.function.BiPredicate;

public class CardAnswerCheck {

    private Box box;
    private Card actualCard;
    private Card cardWithAnswer;

    CardAnswerCheck(Box box, Card actualCard, Card cardWithAnswer) {
        this.box = box;
        this.actualCard = actualCard;
        this.cardWithAnswer = cardWithAnswer;
    }

    public ResultOfLearn checkCorrectnessOfAnswer(BiPredicate<Card, Card> wordComparator) {
        boolean result = wordComparator.test(cardWithAnswer,actualCard);
        changeBucketAndIteration(result);
        return result ? ResultOfLearn.success(actualCard) : ResultOfLearn.fail(actualCard);
    }

    private void changeBucketAndIteration(boolean result) {
        int actualBucket = actualCard.getBucket();
        int actualDayOfLearn = box.getDayOfLearn();

        if (result == true) {
            if (actualBucket == 5) {
                actualCard.setBucket(5);
            } else actualCard.setBucket(actualBucket + 1);
        } else actualCard.setBucket(1);
        actualCard.setIteration(actualDayOfLearn);
    }
}

