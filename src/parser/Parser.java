package parser;

import java.util.*;
import java.io.File;
import format.CONLLCorpus;
import format.Constants;
import format.Word;
import guide.*;
import java.io.IOException;

import sun.misc.Regexp;
import wekaglue.WekaGlue;

/**
 *
 * @author Pierre Nugues
 */
public class Parser {

    ParserState parserState;
    Guide oracle;

    Parser(ParserState parserState, Guide oracle) {
        this.parserState = parserState;
        this.oracle = oracle;
    }

    public List<Word> parse() {
        String transition;

        while (!parserState.queue.isEmpty()) {
            String performedTransition = "sh";
            transition = oracle.predict();
            // Executes the predicted transition. If not possible, then shift
            String[] splitted = transition.replace(".", ",").split(",");
            String func = "_";
            if (splitted.length > 1)
                func = splitted[1];

            if (transition.contains("la")) {
                if (parserState.canLeftArc()){
                    performedTransition = transition;
                    parserState.doLeftArc(func);
                }else {
                    parserState.doShift();
                }
            }else if (transition.contains("re")) {
                if (parserState.canReduce()){
                    performedTransition = transition;
                    parserState.doReduce();
                }else {
                    parserState.doShift();
                }
            }else if (transition.contains("ra")) {
                    performedTransition = transition;
                    parserState.doRightArc(func);
            } else {
                parserState.doShift();
            }
            parserState.addTransition(performedTransition);
        }

        // We empty the stack. When words have no head, we set it to root
        while (parserState.stack.size() > 1) {
            if (parserState.canReduce()) {
                parserState.doReduce();
            } else {
                parserState.doReduceAndSetRoot();
            }
        }
        // In the end, we build the word list
        // All the words must have a head
        // otherwise, the graph would not be connected.
        // Only the root in the stack should have no head
        for (int i = 0; i < parserState.wordList.size(); i++) {
            boolean hasHead = false;
            for (int j = 0; j < parserState.depGraph.size(); j++) {
                if (parserState.wordList.get(i).getId() == parserState.depGraph.get(j).getId()) {
                    parserState.wordList.get(i).setHead(parserState.depGraph.get(j).getHead());
                    hasHead = true;
                    break;
                }
            }
            if (!hasHead) {
                parserState.wordList.get(i).setHead(0);
            }
        }

        boolean printGraph = false;
        if (printGraph) {
            for (int i = 0; i < parserState.wordList.size(); i++) {
                System.out.print(parserState.wordList.get(i).getForm() + " ");
            }
            System.out.println();
            for (int i = 0; i < parserState.transitionList.size(); i++) {
                System.out.print(parserState.transitionList.get(i) + " ");
            }
            System.out.println();
            for (int i = 0; i < parserState.depGraph.size(); i++) {
                System.out.print(parserState.depGraph.get(i).getId() + ", " + parserState.depGraph.get(i).getHead() + " " + parserState.depGraph.get(i).getForm() + " ");
            }
            System.out.println();
        }
        parserState.wordList.remove(0);
        return parserState.wordList;
    }

    public static void main(String[] args) throws IOException {
        File testSet = new File(Constants.TEST_SET);
        CONLLCorpus testCorpus = new CONLLCorpus();
//        WekaGlue wekaModel2 = new WekaGlue();
        WekaGlue wekaModel = new WekaGlue();
        WekaGlue wekaModel6 = new WekaGlue();


        List<List<Word>> sentenceList;
//        List<List<Word>> parsedList2 = new ArrayList<List<Word>>();
        List<List<Word>> parsedList = new ArrayList<List<Word>>();
        List<List<Word>> parsedList6 = new ArrayList<List<Word>>();

//        Parser parser2;
        Parser parser;
        Parser parser6;
//        ParserState parserState2;
        ParserState parserState;
        ParserState parserState6;
        Guide oracle2;
        Guide oracle;
        Guide oracle6;

        if (testSet.exists()) {
            System.out.println("Loading file...");
        } else {
            System.out.println("File does not exist, exiting...");
            return;
        }
        sentenceList = testCorpus.loadFile(testSet);


//        wekaModel2.create(Constants.ARFF_MODEL_2COL, Constants.ARFF_FILE_2COL);
//        wekaModel.create(Constants.ARFF_MODEL, Constants.ARFF_FILE);
        wekaModel6.create(Constants.ARFF_MODEL_6COL, Constants.ARFF_FILE_6COL);

        System.out.println("Parsing the sentences...");
        for (int i = 0; i < sentenceList.size(); i++) {
//            parserState2 = new ParserState(sentenceList.get(i));
//            parserState = new ParserState(sentenceList.get(i));
            parserState6 = new ParserState(sentenceList.get(i));
//            oracle2 = new Guide2(wekaModel2, parserState2);
//            oracle = new Guide4(wekaModel, parserState);
            oracle6 = new Guide6(wekaModel6, parserState6);
//            parser2 = new Parser(parserState2, oracle2);
//            parser = new Parser(parserState, oracle);
            parser6 = new Parser(parserState6, oracle6);
//            parsedList2.add(parser2.parse());
//            parsedList.add(parser.parse());
            parsedList6.add(parser6.parse());
        }

//        testCorpus.saveFile(new File(Constants.TEST_SET_PARSED_2COL), parsedList2);
//        testCorpus.saveFile(new File(Constants.TEST_SET_PARSED), parsedList);
        testCorpus.saveFile(new File(Constants.TEST_SET_PARSED_6COL), parsedList6);
        System.exit(0);
    }
}
