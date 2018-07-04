package dnd.Utils;

import java.util.Arrays;
import java.util.Random;


public class DR {
    //Создадим еще один массив для хранения самих характеристик
    private int chars[] = new int[6];

    private int seed = (int) (Math.random() * 10 + 1);
    public int getSeed() {return seed;}

    public Random rnd = new Random(getSeed());
    public int getChars(int i) {return chars[i];}



    public void DR(){
        int i, j, k;
        int a[][] = new int[6][4];

        //Создадим двухмерный массив из случайных чисел на D6
        for (j = 0; j < 6; j++){
            for (k = 0; k < 4; k++){
                a[j][k] = rnd.nextInt(6) + 1;
            }
        }
        
        //Отсортируем строки данного массива
        for (i = 0; i < 6; i++){
            Arrays.sort(a[i]);
        }
        //Наполним массив суммами строк двумерного массива a
        for (j = 0; j < 6; j++) {
            int sum = 0;
            for ( k = 1; k < 4; k++){
                sum += a[j][k];
            }
            chars[j] = sum;
        }
    }
}