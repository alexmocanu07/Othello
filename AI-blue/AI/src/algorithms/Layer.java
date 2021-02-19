package algorithms;

import java.util.Random;

public class Layer {
    private int neurons;
    private int nextNeurons;
    private double[][] weights;
    private double[] outputs;
    private double[] gradients;

    public Layer(int neurons, int nextNeurons) {
        this.neurons = neurons;
        this.nextNeurons = nextNeurons;
        weights = new double[neurons][nextNeurons];
        outputs = new double[neurons];
        gradients = new double[neurons];
    }

    public void initialize(){
        Random r = new Random();
        for(int i = 0; i < neurons; i++)
            for(int j = 0; j < nextNeurons; j++){
                weights[i][j] = -0.5 + 1 * r.nextDouble();
            }
    }

    public double computeOutput(int neuron, double[] input){
        double output = 0;
        for(int i = 0; i < weights[neuron].length; i++) {
            output += weights[neuron][i] * input[i];
        }
        return sigmoidFunction(output);
    }

    public double sigmoidFunction(double value){
        return 1.0 / (1.0 + Math.exp(-value));
    }

    public double sigmoidDerivative(double value){
        return value * (1.0 - value);
    }

    public void setNeuronOutput(int neuron, double output){
        this.outputs[neuron] = output;
    }
    public double getNeuronOutput(int neuron){
        return this.outputs[neuron];
    }

    public void setNeuronGradient(int neuron, double gradient){
        this.gradients[neuron] = gradient;
    }
    public double getNeuronGradient(int neuron){
        return this.gradients[neuron];
    }

    public int getNeurons() {
        return neurons;
    }

    public void setNeurons(int neurons) {
        this.neurons = neurons;
    }

    public int getNextNeurons() {
        return nextNeurons;
    }

    public void setNextNeurons(int nextNeurons) {
        this.nextNeurons = nextNeurons;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    public double[] getOutputs() {
        return outputs;
    }

    public void setOutputs(double[] outputs) {
        this.outputs = outputs;
    }

    public double[] getGradients() {
        return gradients;
    }

    public void setGradients(double[] gradients) {
        this.gradients = gradients;
    }
}
