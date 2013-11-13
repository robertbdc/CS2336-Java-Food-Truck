import javax.swing.*;
import javax.swing.filechooser.*;

class PickFile extends JFrame
{
   private String fileName = "";
   private String pathName = "";
   
   public PickFile() {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
         "Text files containing data", "txt");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         fileName = chooser.getSelectedFile().getName();
         pathName = chooser.getSelectedFile().getPath();
      }
      else {
         // Cancel: leave filename blank
         fileName = "";
         pathName = "";
      }
   }
   
   public String getFullFileName() {
      return pathName;
   }
   
   public String getFileName() {
      return fileName;
   }
   
   // future, if we just need path
   //public String getPathName() {
   //   return pathName.parsesomehow();
   //}      

}
