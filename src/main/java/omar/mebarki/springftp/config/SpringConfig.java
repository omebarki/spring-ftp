package omar.mebarki.springftp.config;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class SpringConfig {
    @Value("${ftp.host.name}")
    String ftpHostName;

    @Value("${ftp.host.port}")
    int ftpHostPort;

    @Value("${ftp.client.mode}")
    int ftpClientMode;

    @Value("${ftp.login}")
    String ftpLogin;

    @Value("${ftp.password}")
    String ftpPassword;

    @Bean
    DefaultFtpSessionFactory ftpClientFactory() {
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        ftpSessionFactory.setBufferSize(100000);
        ftpSessionFactory.setHost(ftpHostName);
        ftpSessionFactory.setPort(ftpHostPort);
        ftpSessionFactory.setClientMode(ftpClientMode);
        ftpSessionFactory.setFileType(2);//binary
        ftpSessionFactory.setUsername(ftpLogin);
        ftpSessionFactory.setPassword(ftpPassword);
        return ftpSessionFactory;
    }

    @Bean
    public Session<FTPFile> ftpClient(SessionFactory<FTPFile> ftpClientFactory) {
        return ftpClientFactory.getSession();
    }
}
