package guide;

import wekaglue.WekaGlue;
import parser.ParserState;

/**
 *
 * @author Pierre Nugues
 */
public class Guide6 extends Guide {

    public Guide6(WekaGlue wekaModel, ParserState parserState) {
        super(wekaModel, parserState);
    }
    // This is a simple oracle that uses the top and second in the stack and first and second in the queue + the Booleans

    public String predict() {
        Features feats = extractFeatures();
        String[] features = new String[8];
        features[0] = feats.getTopPostagStack();
        features[1] = feats.getSecondPostagStack();
        features[2] = feats.getThirdPostagStack();
        features[3] = feats.getFirstPostagQueue();
        features[4] = feats.getSecondPostagQueue();
        features[5] = feats.getThirdPostagQueue();
        features[6] = String.valueOf(feats.getCanLA());
        features[7] = String.valueOf(feats.getCanRE());
        return wekaModel.classify(features);
    }
}
