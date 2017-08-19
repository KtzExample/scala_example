package org.example.ktz.hackerrank.day30.inheritance;

class Student extends Person{
    private int[] testScores;

    Student(String firstName, String lastName, int identification, int[] testScores) {
        super(firstName, lastName, identification);
        this.testScores = testScores;
    }

    private Double scoreAverage(int[] testScores) {
        Integer sum = 0;
        for(Integer ele : testScores) {
            sum += ele;
        }

        return sum.doubleValue() / testScores.length;
    }



    public Character calculate() {
        Double average = scoreAverage(testScores);

        if(average >= 90 && average <= 100) return 'O';
        else if(average >= 80 && average < 90) return 'E';
        else if(average >= 70 && average < 80) return 'A';
        else if(average >= 55 && average < 70) return 'P';
        else if(average >= 40 && average < 55) return 'D';
        else return 'T';
    }
}
