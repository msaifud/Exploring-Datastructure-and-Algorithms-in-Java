import java.io.*;

public class Assignment1 {

	public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
	  {
		  int C[][] = new int[size][size];
		  
		  if(size==1) {
			  C[0][0] = A[0][0] * B[0][0];
			  return C;
		  }
		  else {
			  int[][] M0 = new int[size/2][size/2];
			  int[][] M1 = new int[size/2][size/2];
			  int[][] M2 = new int[size/2][size/2];
			  int[][] M3 = new int[size/2][size/2];
			  int[][] M4 = new int[size/2][size/2];
			  int[][] M5 = new int[size/2][size/2];
			  int[][] M6 = new int[size/2][size/2];
			  int[][] A11 = new int[size/2][size/2];
			  int[][] B11 = new int[size/2][size/2];
			  
			  for(int i = size/2; i < size; i++) {
				  for(int j = size/2; j < size; j++) {
					  A11[i - size/2][j - size/2] = A[i][j];
					  B11[i - size/2][j - size/2] = B[i][j];
				  }
			  }
			  
			  M0 = denseMatrixMult(sum(A, A, 0, 0, size/2, size/2, size/2), sum(B, B, 0, 0, size/2, size/2, size/2), size/2);
			  M1 = denseMatrixMult(sum(A, A, size/2, 0, size/2, size/2, size/2), B, size/2);
			  M2 = denseMatrixMult(A, sub(B, B, 0, size/2, size/2, size/2, size/2), size/2);
			  M3 = denseMatrixMult(A11, sub(B, B, size/2, 0, 0, 0, size/2), size/2);
			  M4 = denseMatrixMult(sum(A, A, 0, 0, 0, size/2, size/2), B11, size/2);
			  M5 = denseMatrixMult(sub(A, A, size/2, 0, 0, 0, size/2), sum(B, B, 0, 0, 0, size/2, size/2), size/2);
			  M6 = denseMatrixMult(sub(A, A, 0, size/2, size/2, size/2, size/2), sum(B, B, size/2, 0, size/2, size/2, size/2), size/2);
			  		  
			  for(int i = 0; i < size; i++) {
				  for(int j = 0; j < size; j++) {
					  if((i < size/2) && (j < size/2)) {
						  C[i][j] = M0[i][j] + M3[i][j] - M4[i][j] + M6[i][j];
					  }
					  if((i < size/2) && (j >= size/2)) {
						  C[i][j] = M2[i][j - size/2] + M4[i][j - size/2];
					  }
					  if((i >= size/2) && (j < size/2)) {
						  C[i][j] = M1[i - size/2][j] + M3[i - size/2][j];
					  }
					  if((i >= size/2) && (j >= size/2)) {
						  C[i][j] = M0[i - size/2][j - size/2] - M1[i - size/2][j - size/2] + M2[i - size/2][j - size/2] + M5[i - size/2][j - size/2];
					  }
				  }
			  }
			  return C;
		  }
	  }
	public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
	int[][] C = new int[n][n];
     for(int i=0; i < n; i++) {
    	 for(int j=0; j < n; j++) {
        	 C[i][j] = A[i + x1][j + y1] + B[i + x2][j + y2];
         }
     }		 
     return C;
			 
  }

	public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n) {
		
		int [][] C = new int [n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				C[i][j] = A[i+x1][j+y1]- B[i+x2][j+y2];
			}
		}
		return C;
	}

	public int[][] initMatrix(int n) {
		int[][] matrix = new int[n][n];
		return matrix;
	}

	public void printMatrix(int n, int[][] A) {
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				System.out.print(A[row][column] + "\t");
			}
			System.out.println();
		}
	}

	public int[][] readMatrix(String filename, int n) throws Exception {
		BufferedReader fileIn = new BufferedReader(new FileReader(filename));
		String[] splitted = new String[n];
		int[][] output = new int[n][n];

		for (int row = 0; row < n; row++) {
			splitted = (fileIn.readLine()).split(" ");
			for (int column = 0; column < n; column++) {
				output[row][column] = Integer.parseInt(splitted[column]);
			}
		}
		return output;
	}

}