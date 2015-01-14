package Structures;

/**
 * Created by Mohammad Sadegh Rasooli.
 * ML-NLP Lab, Department of Computer Science, Columbia University
 * Date Created: 1/13/15
 * Time: 11:10 PM
 * To report any bugs or problems contact rasooli@cs.columbia.edu
 */

public class Options {
    public boolean useBeamSearch;
    public boolean train;
    public boolean tag;
    public int beamWidth;
    public int trainingIter;
    public String delim;
    public String modelPath;
    public String trainPath;
    public String devPath;
    public String inputPath;
    public String outputPath;

    public Options() {
        this.useBeamSearch = true;
        train=true;
        tag=false;
        beamWidth=5;
        trainingIter=10;
        delim="_";
        modelPath="";
        trainPath="";
        devPath="";
        inputPath="";
        outputPath="";
    }

    public Options(String[] args){
        this();
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-viterbi"))
                useBeamSearch=false;
            if(args[i].startsWith("beam:"))
                beamWidth = Integer.parseInt(args[i].substring(args[i].indexOf("beam:")+5));
            if(args[i].startsWith("iter:"))
                trainingIter = Integer.parseInt(args[i].substring(args[i].indexOf("iter:")+5));
            if(args[i].equals("train"))
                train=true;
            if(args[i].equals("tag"))
                tag=true;
            if(args[i].equals("-model") && i<args.length-1)
                modelPath=args[i+1];
            if(args[i].equals("-input") && i<args.length-1) {
                trainPath = args[i + 1];
                inputPath = args[i + 1];
            }
            if(args[i].equals("-output") && i<args.length-1)
                outputPath = args[i + 1];
            if(args[i].equals("-dev") && i<args.length-1)
                devPath = args[i + 1];
            if(args[i].equals("-delim") && i<args.length-1)
                delim = args[i + 1];
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if (train) {
            output.append("train iterations: " + trainingIter + "\n");
            output.append("train file: " + trainPath + "\n");
            output.append("model file: " + trainPath + "\n");
            if (!useBeamSearch)
                output.append("using Viterbi algorithm\n");
            else
                output.append("using beam search algorithm with beam size:" + beamWidth + "\n");

        } else if (tag) {
            output.append("train iterations: " + trainingIter + "\n");
            output.append("input file: " + inputPath + "\n");
            output.append("output file: " + outputPath + "\n");
            output.append("model file: " + trainPath + "\n");
            if (!useBeamSearch)
                output.append("using Viterbi algorithm\n");
            else
                output.append("using beam search algorithm with beam size:" + beamWidth + "\n");
        }
        return output.toString();
    }

    public String showHelp(){
        StringBuilder output = new StringBuilder();
        output.append("* Train a tagger:\n");
        output.append(">>  java -jar SemiSupervisedTagger.jar train -input [input-file] -model [model-file]\n");
        output.append("** Other Options:\n");
        output.append("     -viterbi   if you want to use Viterbi decoding (default: beam decoding)\n");
        output.append("     -delim [delim]   put delimiter string in [delim] for word tag separator (default _) e.g. -delim / \n");
        output.append("     beam:[#b]  put a number [#b] for beam size (default:5); e.g. beam:10\n");
        output.append("     iter:[#i]  put a number [#i] for training iterations (default:10); e.g. iter:20\n");
        output.append("\n\n");

        output.append("* Tag a file:\n");
        output.append(">>  java -jar SemiSupervisedTagger.jar tag -input [input-file] -model [model-file] -output [output-file]\n");
        output.append("** Other Options:\n");
        output.append("     -delim [delim]   put delimiter string in [delim] for word tag separator (default _) e.g. -delim / \n");

        return output.toString();
    }
}