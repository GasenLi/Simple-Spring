import org.gasen.IOC.Factory.PropertyEditorSupport;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport{
    private String format = "yyyy-MM-dd";

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    @Test @Parameters("arg0")
    public void setAsText(@Optional("2018-12-12") String arg0) throws IllegalArgumentException {
        //System.out.println("arg0: " + arg0);
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            Date d = sdf.parse(arg0);
            this.setValue(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void speak(){
        System.out.println(this.getValue());
    }
}
