package src;
import java.util.Scanner;

public class Matrix {

    private int row,col; //m itu baris,n itu kolom
    
    private double[][] matrix;//ax
    
    public static final double decPoint = 10000000000d;
    Scanner scanner = new Scanner(System.in);
    /* ********** KONSTRUKTOR ********** */

    public Matrix(int baris, int kolom) {
        this.row = baris;
        this.col = kolom;
        this.matrix = new double[baris][kolom];
    }
    
    public Matrix(double[][] matrix) {
        // Konstruktor dari tabel
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[0].length; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        
    }
}

    
    
    

    
    /* ********** INPUT/OUTPUT MATRIX ********** */
    public void readMatrix() //procedure baca matrix dari input keyboard
    {
       

        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {   
                System.out.println("Isi elemen ke [" + (i) + "]" +"[" + (j) + "] : ");
                double elemen = scanner.nextDouble();
                this.matrix[i][j] = elemen;//koef a[i][j]
                
            }
        }
        System.out.print("\n");
    }

   

    public void printMatrix() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.matrix[i][j]+=0; //menghilangkan -0
                System.out.format(" %.2f ", this.matrix[i][j]);
            }

            System.out.print("\n");
        }
        } 
    


    // *** Operasi perkalian matriks ***
    public static Matrix multiple (Matrix m, double k){
        Matrix result = new Matrix(m.row,m.col);
        for(int i=0;i<m.row;++i){
            for(int j=0;j<m.col;++j){
                result.matrix[i][j] *= k;
            }
        }
        return result;
    }


    
    
    /* ********** TIPE MATRIX ********** */
    public static Matrix Identitas(int N) {
        Matrix M = new Matrix(N, N);
        for (int i = 0; i < N; i++)
        {
            for (int j =0;j<N;j++)
            {
                M.matrix[i][j]=0;
            
            }
            M.matrix[i][i] = 1;
        }
        return M;
    }



    // Operasi Baris Elementor
    public void swap (int row1, int row2){
        double [] temp = matrix[row1];
        matrix [row1] = matrix[row2];
        matrix[row2] = temp;
    }

    public void timesrow(int row,  double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row][j] *= k;
        }
    }

    public void plustimesrow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] += matrix[row2][j]*k;
        }
    }

    public void minustimesrow(int row1, int row2, double k){
        int j;
        for (j = 0; j<col; j++){
            matrix[row1][j] -= matrix[row2][j]*k;
        }
    }

    public void plusrow(int row1, int row2){
        plustimesrow(row1,row2,1);
    }

    public void minusrow(int row1, int row2){
        minustimesrow(row1,row2,1);
    }

    /* ********** SIFAT MATRIX ********** */
    public boolean IsIdentitas() {
        boolean out = true;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (!(((i == j) && matrix[i][j] == 1) || ((i != j) && matrix[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

    // ***Operasi MATRIX***
    public static void main(String args[])
    {

        Matrix testSpl = new Matrix(3,4);
        testSpl.readMatrix();
        testSpl.printMatrix();
        // testSpl.eselonBaris();
        // System.out.println("\n");
        // testSpl.printMatrix();
        // testSpl.eselonBarisReduksi();
        System.out.println("\n");
        // testSpl.printMatrix();
        testSpl.inverseGausJordan();
        testSpl.printMatrix();

}

    public static void Copy(Matrix in, Matrix out){
        out.row = in.row;
        out.col = in.col;
        out.matrix = new double[in.row][in.col];


        for(int i=0; i<in.row;++i){
            for(int j=0; j<in.col; ++j){
                out.matrix[i][j] = in.matrix[i][j];
            }
        }
    }



    public void eselonBaris() {
        int rowTemp = 0; // Menunjukkan baris saat ini
        int colTemp = 0; // Menunjukkan kolom saat ini

        while (rowTemp < this.row && colTemp < this.col) {
            // Cari baris dengan elemen pertama yang bukan nol
            int nonZeroRow = rowTemp;
            while (nonZeroRow < this.row && matrix[nonZeroRow][colTemp] == 0) {
                nonZeroRow++;
            }

            if (nonZeroRow < this.row) {
                // Swap baris dengan baris yang memiliki elemen pertama yang bukan nol
                swap(rowTemp, nonZeroRow);

                // Buat elemen pertama menjadi 1
                double pivot = matrix[rowTemp][colTemp];
                timesrow(rowTemp, 1.0 / pivot);

                // Eliminasi baris-baris di bawahnya
                for (int i = rowTemp + 1; i < this.row; i++) {
                    double factor = matrix[i][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }

                rowTemp++; // Pindah ke baris berikutnya
            }

            colTemp++; // Pindah ke kolom berikutnya
        }
    }

   

    public void eselonBarisReduksi() {
        eselonBaris(); // Panggil eselonBaris terlebih dahulu untuk mendapatkan bentuk eselon baris

        // Mulai dari baris terbawah, kerja ke atas untuk menghilangkan elemen di atas
        // pivot
        for (int rowTemp = this.row - 1; rowTemp > 0; rowTemp--) {
            int colTemp = 0;
            while (colTemp < this.col && matrix[rowTemp][colTemp] == 0) {
                colTemp++;
            }

            if (colTemp < this.col) {
                // Eliminasi elemen di atas pivot
                for (int i = rowTemp - 1; i >= 0; i--) {
                    double factor = matrix[i][colTemp];
                    minustimesrow(i, rowTemp, factor);
                }
            }
        }
    }






    
    // public void approximate()
    // {
    //     for (int i =0;i<row;i++)
    //     {
    //         for (int j =0 ; j<col;j++)
    //         {
    //             matrix[i][j]=(Math.round(matrix[i][j] * decPoint)/decPoint);
    //         }
    //     }
    // }

    public boolean isIdentity(int N)//mengecheck apakah matriks[N][N] adalah matriks identitas
    {
        boolean check = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!(((i == j) && matrix[i][j] == 1) || ((i != j) && matrix[i][j] == 0))) {
                    check = false;
                }
            }
        }
        return check;
    }

    //  public static Matrix Identitas(int N) {
    //     Matrix M = new Matrix(N, N);
    //     for (int i = 0; i < N; i++)
    //         M.matrix[i][i] = 1;
    //     return M;
    // }

    public void inverseGausJordan()
    {
        //asumsi matrix NxN
        Matrix inverse = new Matrix(this.row,(this.row)*2);
        Matrix matrixIdentity = new Matrix(this.row,this.row);
        matrixIdentity=Identitas(this.row);
        int k=0,l=0;
        //buat matriks baru yang berisi gabungan matrix dan matrix identitas
        for (int i = 0;i<this.row;i++)
        {
            
            for (int j = 0 ;j<(this.row)*2;j++)
            {
                if (j<(this.col-1))
                {
                    inverse.matrix[i][j]=this.matrix[i][j];
                }
                else 
                {
                    inverse.matrix[i][j]=matrixIdentity.matrix[i][l];
                    l++;
                    if (l>this.row-1)
                    {
                        l =0;
                    }
                }
                
            }
            
        }
       //melakukan operasi eselon baris reduksi pada matrix inverse
        inverse.eselonBarisReduksi();

        //mengecek apakah adanya baris yang bernilai 0
        // int [] zeroCount=new int[this.row];
        //menghitung nilai 0 pada tiap baris
        int temp;
        boolean inverseExist = true;
        for(int i = 0 ; i <this.row;i++)
        {
            temp =0;
            for (int j =0 ;j<this.row;j++)
            {
                if (inverse.matrix[i][j]==0)
                {
                    temp++;
                }
            }
            if (temp == this.row)
            {
                
                inverseExist=false;
                break;
            }
        }
        inverse.printMatrix();
        if (inverseExist)
        {
            //mengembalikan hasil inverse ke this.matrix
            for (int i = 0;i < this.row;i++)
            {
                for ( int j = this.row;j<(this.row)*2;j++)
                {
                    this.matrix[i][k]=inverse.matrix[i][j];
                    k++;
                    if (k>this.row-1)
                        {
                            k =0;
                        }
                }
            }
            //contoh solusi jadi
            double[] solusi = new double[this.row];
            double temp1=0;
            int x =0;
            for (int i = 0;i < this.row;i++)
            {
                temp1=0;
                for ( int j = 0;j<this.col-1;j++)
                {
                    temp1+=this.matrix[i][j]*this.matrix[x][this.col-1];
                    x++;
                    if (x>this.row-1)
                    {
                        x=0;
                    }
                }
                solusi[i]=temp1;
            }
            for (int i =0;i<this.row;i++)
            {
                System.out.println("solusi  X" + (i+1) + " : " + solusi[i]);
            }
        }
        else
        {
            System.out.println("KONTOL!!!");
            
        }
        
   

    }







}

