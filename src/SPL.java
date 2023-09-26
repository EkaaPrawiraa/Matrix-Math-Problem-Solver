package src;
import java.util.*;



public class SPL {
    static Scanner scan = new Scanner(System.in);

    public static void SPLGaus(){
        System.out.print("M:");
        int m = scan.nextInt();
        System.out.print("N:");
        int n = scan.nextInt();
        Matrix mat = new Matrix(m, n+1);
        mat.readMatrix();
        eksekusiGauss(mat);
    }

    public static void eksekusiGauss(Matrix mat){
        // Matrix M = new Matrix(mat);
        // M.Copy(mat);
        mat.eselonBaris();

        for(int i=0;i<=mat.GetLastIdxBrs();++i){
            for(int j=0;j<=mat.GetLastIdxKol()-1;++j){
                if(mat.GetElmt(i, mat.GetLastIdxKol()-1)==0){
                    if(mat.GetElmt(i, mat.GetLastIdxKol())==0){//solusi banyak
                        parametricSolution();
                        break;
                    }else{//solusi tidak ada
                        System.out.println("Solusi tidak ada.");
                        break;
                    }
                } else {
                    
                }


            }
        }
        // tidak ada solusi
        mat.printMatrix();

    }
    
    public static void parametricSolution(){

    }


    public static void main(String[] args){
        SPL.SPLGaus();
    }
    
}





    // void menuSPL (int pilihan){
    //     switch(pilihan){
    //         case 1:
    //             System.out.println("Solusi SPL menggunakan eliminasi Gauss:");
    //             // eksekusi metode
    //             break;
    //         case 2:
    //             System.out.println("Solusi SPL menggunakan eliminasi Gauss-Jordan:");
    //             // eksekusi metode 
    //             break;
    //         case 3:
    //             System.out.println("Solusi SPL menggunakan eliminasi Matriks Balikan:");
    //             // eksekusi metode 
    //             break;
    //         case 4:
    //             System.out.println("Solusi SPL menggunakan eliminasi Kaidah Crammer:");
    //             break;
    //             // eksekusi metode 
    //     }
    // }



    

