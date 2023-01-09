import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfManager {

    //user selecting files from device 
    public static void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
        
            try{
                File file = fileUpload.getSelectedFile();
                
                FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
                PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                System.out.println(docText);

                extractInfo(docText);

                pdfDocument.close();//closing document
                fis.close();//closing file input stream
                
            } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex);//printing error message 
            } 
        }
    }

    private static void extractInfo(String doc){

        String [] lines = doc.split("\n");//obtains every new lines in the document text
        String name, email, mon, tues, wed, thur, fri;//strings for collecting information from each player

        for(String line : lines){//itterates through every line in document 

            String[] parts = line.split(",");//splits the lines based on commas

            //sections everything out
            name = parts[0].substring(1);
            email = parts[1].substring(1);
            mon = parts[2].substring(1);
            tues = parts[3].substring(1);
            wed = parts[4].substring(1);
            thur = parts[5].substring(1);
            fri = parts[6].substring(1);

            setPlayerTime(mon);
        }
    }

    private static void setPlayerTime(String avail){
        
    }

}//end of pdfManager
