import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class TextFile {
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public TextFile(String filePath) throws IOException {
        if (filePath.substring(filePath.lastIndexOf(".")).equals(".txt")) {
            this.file = new File(filePath);
            this.fileReader = new FileReader(this.file);
            this.bufferedReader = new BufferedReader(this.fileReader);
        } else {
            throw new IOException("El archivo especificado no es un archivo de texto");
        }
    }

    public TextFile(File file) throws IOException {
        String filePath = file.getPath();
        if (filePath.substring(filePath.lastIndexOf(".")).equals(".txt")) {
            this.file = file;
            this.fileReader = new FileReader(this.file);
            this.bufferedReader = new BufferedReader(this.fileReader);
        } else {
            throw new IOException("El archivo especificado no es un archivo de texto");
        }
    }

    public String readLine() throws IOException {
        return this.bufferedReader.readLine();
    }

    public void close() throws IOException {
        this.fileReader.close();
        this.bufferedReader.close();
    }
}
