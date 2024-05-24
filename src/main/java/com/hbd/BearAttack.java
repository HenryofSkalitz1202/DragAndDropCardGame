package com.hbd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hbd.PetakLadang.PetakLadang;
import com.hbd.Kartu.Makhluk.Makhluk;

public class BearAttack{
    public static List<int[]> generateAttackedCells(){
        List<int[]> attackedCells = new ArrayList<>();

        int maxNumRow = 3;
        int minNumRow = 1;
        int maxNumCol = 3;
        int minNumCol = 1;
        Random rand = new Random();
        int numRow = rand.nextInt(maxNumRow - minNumRow + 1) + minNumRow;
        if (numRow == 3) {
            maxNumCol = 2;
        }
        int numCol = rand.nextInt(maxNumCol - minNumCol + 1) + minNumCol;

        int startRow = rand.nextInt(3 - numRow + 1) + numRow;
        int startCol = rand.nextInt(4 - numCol + 1) + numCol;

        System.out.println("Cells attacked:");

        for (int i = startRow; i > startRow - numRow; i--) {
            for (int j = startCol; j > startCol - numCol; j--) {
                System.out.printf("(%d, %d)\n", j, i);
                attackedCells.add(new int[]{i, j});
            }
        }

        return attackedCells;
    }

    public static void attack(PetakLadang ladang, List<int[]> attackedCells) throws Exception{
        // List<int[]> attackedCells = generateAttackedCells();
        boolean trapped = false;

        for (int[] cell : attackedCells){
            Makhluk makhlukCell = ladang.getMakhluk(cell[1], cell[0]);
            if(makhlukCell == null){
                System.out.printf("(%d, %d) is empty\n", cell[1], cell[0]);
            }else{
                boolean isProtected = false;
                for(String effect : makhlukCell.getEffect()){
                    if(effect == "Protect"){
                        System.out.printf("(%d, %d) is protected\n", cell[1], cell[0]);
                        isProtected = true;
                        break;
                    }else if(effect == "Trap"){
                        System.out.printf("(%d, %d) traps beruang!!\n", cell[1], cell[0]);
                        trapped = true;
                        break;
                    }
                }

                if(trapped){
                    break;
                }
                
                if(!isProtected){
                    System.out.printf("(%d, %d) is attacked\n", cell[1], cell[0]);
                    ladang.setNull(cell[1], cell[0]);
                }
            }
        }
    }
}