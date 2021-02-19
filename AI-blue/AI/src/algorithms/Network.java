package algorithms;


import java.util.ArrayList;
import java.util.List;

public class Network {
    private int inputNeurons, hiddenNeurons, outputNeurons;
    private int epochNumber;
    private double learnRate;
    private Layer hiddenLayer, outputLayer;



    public Network(int inputNeurons, int hiddenNeurons, int outputNeurons, int epochNumber,  double learnRate) {
        this.inputNeurons = inputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        this.outputNeurons = outputNeurons;
        this.epochNumber = epochNumber;
        this.learnRate = learnRate;
        this.hiddenLayer = new Layer(hiddenNeurons, inputNeurons);
        this.hiddenLayer.initialize();
        this.outputLayer = new Layer(outputNeurons, hiddenNeurons);
        this.outputLayer.initialize();
    }


    public double forwardPropagate(int[] input){
        List<Double> newInput = new ArrayList<>();
        double[] inputs = new double[20];
        for(int i = 0; i < input.length; i++)
            inputs[i] = (double)input[i];
        for(int i = 0; i < hiddenLayer.getNeurons(); i++){
            hiddenLayer.setNeuronOutput(i, hiddenLayer.computeOutput(i, inputs));
            newInput.add(hiddenLayer.getNeuronOutput(i));
        }

        for(int i = 0; i < newInput.size(); i++)
            inputs[i] = newInput.get(i);


        outputLayer.setNeuronOutput(0, outputLayer.computeOutput(0,inputs));
        return outputLayer.getNeuronOutput(0);

    }

    public void backwardPropagate(double expected){

        double error = expected - outputLayer.getNeuronOutput(0);

        outputLayer.setNeuronGradient(0, error * outputLayer.sigmoidDerivative(outputLayer.getNeuronOutput(0)));

        List<Double> sums = new ArrayList<>();
        for(int i = 0; i < hiddenLayer.getNeurons(); i++){
            double currentsum = outputLayer.getWeights()[0][i] * outputLayer.getNeuronGradient(0);
//            for(int j = 0; j < outputLayer.getNeurons(); j++){
//                currentsum += (outputLayer.getWeights()[j][i] * outputLayer.getNeuronGradient(j));
//            }
            sums.add(currentsum);
        }
        for(int i = 0; i < hiddenLayer.getNeurons(); i++){
            hiddenLayer.setNeuronGradient(i, sums.get(i) * hiddenLayer.sigmoidDerivative(hiddenLayer.getNeuronOutput(i)));
        }

    }

    public void updateWeights(int[] input){
        List<Double> newInput = new ArrayList<>();
        for(int i = 0; i < input.length; i++) newInput.add((double)input[i]);

        for(int i = 0; i < hiddenLayer.getNeurons(); i++){
            double[][] weights = hiddenLayer.getWeights();
            for(int j = 0; j < newInput.size(); j++){
                weights[i][j] += this.learnRate * hiddenLayer.getNeuronGradient(i) * newInput.get(j);
            }
            hiddenLayer.setWeights(weights);
        }
        newInput = new ArrayList<>();
        for(int i = 0; i < hiddenLayer.getNeurons(); i++)
            newInput.add(hiddenLayer.getOutputs()[i]);

//        for(int i = 0; i < outputLayer.getNeurons(); i++) {
            double[][] weights = outputLayer.getWeights();
            for (int j = 0; j < newInput.size(); j++) {
                weights[0][j] += this.learnRate * outputLayer.getNeuronGradient(0) * newInput.get(j);
            }
            outputLayer.setWeights(weights);
//        }
    }

    public void train(){
        for(int epoch = 0; epoch < this.epochNumber; epoch++){

        }
    }

    public double predict(int[] input){
        return forwardPropagate(input);
//        double maxVal = output.get(0);
//        for(int i = 1; i < output.size(); i++){
//            maxVal = Math.max(maxVal, output.get(i));
//        }
//        System.out.println(output);
//        return output.indexOf(maxVal);
    }


    public int getInputNeurons() {
        return inputNeurons;
    }

    public void setInputNeurons(int inputNeurons) {
        this.inputNeurons = inputNeurons;
    }

    public int getHiddenNeurons() {
        return hiddenNeurons;
    }

    public void setHiddenNeurons(int hiddenNeurons) {
        this.hiddenNeurons = hiddenNeurons;
    }

    public int getOutputNeurons() {
        return outputNeurons;
    }

    public void setOutputNeurons(int outputNeurons) {
        this.outputNeurons = outputNeurons;
    }

    public int getEpochNumber() {
        return epochNumber;
    }

    public void setEpochNumber(int epochNumber) {
        this.epochNumber = epochNumber;
    }



}

