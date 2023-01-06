import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfManager {
    private int next;

    //user selecting files from device 
    private void readInFiles(){

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

    private void extractInfo(String doc){

        //strings for collecting information from each player
        String name, email, mon, tues, wed, thur, fri;
        next = 0;

        //collecting information
        name = locate (doc, ", ", next);
        //email = locate (doc, ", ", next);
        //mon = locate (doc, ", ", next);
        //tues = locate (doc, ", ", next);
        //wed = locate (doc, ", ", next);
        //thur = locate (doc, ", ", next);
        //fri = locate (doc, ", ", next);

        System.out.println(name);
        //System.out.println(email);
       // System.out.println(mon);
        //System.out.println(tues);
        //System.out.println(wed);
        //System.out.println(thur);
       // System.out.println(fri);
    }

    private String locate(String doc, String id, int idInt){
        String result;
        next = idInt;

        int start = doc.indexOf(id, next);
        int end = doc.indexOf(", ", start);
        result = doc.substring(start, end);

        return result;
    }

}//end of pdfManager
