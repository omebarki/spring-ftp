package omar.mebarki.springftp;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.integration.file.remote.session.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SpringBootApplication
public class SpringFtpApplication {

    public static final String PATH = "/ftp/";
    @Autowired
    private Session<FTPFile> ftpFileSession;

    public static void main(String[] args) {
        SpringApplication.run(SpringFtpApplication.class, args);
    }

    @EventListener
    public void onReady(ApplicationReadyEvent e) {
        try {
            System.out.println("Hello!");
            FTPFile[] ftpFiles = ftpFileSession.list(PATH);
            for (FTPFile file : ftpFiles) {
                System.out.println("------------" + file.getName());
                if (file.isFile()) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ftpFileSession.read(PATH + file.getName(), byteArrayOutputStream);
                    System.out.println(byteArrayOutputStream.toString());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
