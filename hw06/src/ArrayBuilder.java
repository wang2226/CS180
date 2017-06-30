public class ArrayBuilder {
    private char[][] letterArray;
    private char baseLetter;
    private int n, m;

    public ArrayBuilder(char baseLetter, int n, int m) {
        this.baseLetter = baseLetter;
        letterArray = new char[n][m];
        this.n = n;
        this.m = m;
    }

    public void build() {
        char firstChar = baseLetter;
        char secondChar;

        for (int i = 0; i < letterArray.length; i++) {
            letterArray[i][0] = firstChar;

            if(firstChar == 'z' || firstChar == 'Z'){
                secondChar = (char) (firstChar - 25);
            }else {
                secondChar = (char) (firstChar + 1);
            }

            for (int j = 1; j < letterArray[i].length; j++) {
                letterArray[i][j] = secondChar;
                if(secondChar == 'z' || secondChar == 'Z'){
                    secondChar = (char)(secondChar - 25);
                } else{
                    secondChar = (char)(secondChar + 1);
                }
            }
            if(firstChar == 'z' || firstChar == 'Z'){
                firstChar = (char)(firstChar - 25);
            } else {
                firstChar = (char)(firstChar + 1);
            }
        }
    }

    public char[][] getLetterArray() {
        char[][] array = new char[n][m];

        for(int i =0; i < n; i++){
            for(int j =0; j < m; j++){
                array[i][j] = letterArray[i][j];
            }

        }
        return array;
    }

    public void printLetterArray() {
        char [][] printArray = getLetterArray();

        for(int i =0; i < n; i++){
            for(int j =0; j < m; j++){
                System.out.print( String.format("%2c", printArray[i][j]));
            }
            System.out.println( "" );
        }
    }

    public static void main(String[] args) {

        ArrayBuilder arrayBuilder = new ArrayBuilder('A',5,5);
        arrayBuilder.build();
        System.out.println( "baseLetter is A, 5 X 5:" );
        arrayBuilder.printLetterArray();

        arrayBuilder = new ArrayBuilder('Z',5,5);
        arrayBuilder.build();
        System.out.println( "baseLetter is Z, 5 X 5:" );
        arrayBuilder.printLetterArray();

        arrayBuilder = new ArrayBuilder('y',6,4);
        arrayBuilder.build();
        System.out.println( "baseLetter is y, 6 X 4:" );
        arrayBuilder.printLetterArray();

        arrayBuilder = new ArrayBuilder('y',4,6);
        arrayBuilder.build();
        System.out.println( "baseLetter is y, 4 X 6:" );
        arrayBuilder.printLetterArray();
    }
}
