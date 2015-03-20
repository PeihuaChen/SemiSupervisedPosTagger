package SemiSupervisedPOSTagger.Structures;

import SemiSupervisedPOSTagger.Learning.AveragedPerceptron;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Mohammad Sadegh Rasooli.
 * ML-NLP Lab, Department of Computer Science, Columbia University
 * Date Created: 1/13/15
 * Time: 12:58 PM
 * To report any bugs or problems contact rasooli@cs.columbia.edu
 */

public class InfoStruct implements Serializable {
    public HashMap<Integer, Float>[][] averagedWeights;
   public HashMap<Integer,HashSet<Integer>> tagDictionary;
    public int tagSize;
    public int featSize;
    public int beamSize;
    public boolean useBeamSearch;

    public InfoStruct(AveragedPerceptron perceptron, boolean useBeamSearch, int beamSize,HashMap<Integer,HashSet<Integer>> tagDictionary) {
        averagedWeights = perceptron.getAveragedWeights();
        tagSize = perceptron.tagSize();
        featSize = perceptron.featureSize();
        this.beamSize=beamSize;
        this.useBeamSearch=useBeamSearch;
        this.tagDictionary=tagDictionary;
    }
}
