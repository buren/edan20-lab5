package guide;

import format.Word;
import wekaglue.WekaGlue;
import parser.ParserState;

/**
 *
 * @author Pierre Nugues
 */
public abstract class Guide {

    WekaGlue wekaModel;
    ParserState parserState;

    Guide(WekaGlue wekaModel, ParserState parserState) {
        this.wekaModel = wekaModel;
        this.parserState = parserState;
    }

    public abstract String predict();

    public Features extractFeatures() {
        Features feats;
        String topPostagStack = "nil";
        String secondPostagStack = "nil";
        String secondPostagQueue = "nil";
        String topStackNextWordPostag = "nil";
        String thirdPostagQueue= "nil";

        if (!parserState.stack.empty()) {
            topPostagStack = parserState.stack.peek().getPostag();

            Word temp = parserState.stack.pop();
            if (!parserState.stack.empty()) {
                secondPostagStack = parserState.stack.peek().getPostag();
            }
            parserState.stack.push(temp);
            topStackNextWordPostag = this.findNextWordPosTagAfterStackTop(parserState.stack.peek());
        }
        if (parserState.queue.size() > 1) {
            secondPostagQueue = parserState.queue.get(1).getPostag();
        }

        if (parserState.queue.size() > 2)
            thirdPostagQueue = parserState.queue.get(2).getPostag();

        return new Features(
                topPostagStack,
                secondPostagStack,
                topStackNextWordPostag,
                parserState.queue.get(0).getPostag(),
                secondPostagQueue,
                thirdPostagQueue,
                parserState.canLeftArc(),
                parserState.canReduce()
        );
    }

    private String findNextWordPosTagAfterStackTop(Word topStackWord){
        for (int i = 0; i < parserState.getWordList().size(); i++){
            if (topStackWord.equals(parserState.getWordList().get(i))) {
                if (i == parserState.getWordList().size()-1){
                    return "nil";
                }else {
                    return parserState.getWordList().get(i+1).getPostag();
                }
            }
        }
        return "nil";
    }
}
