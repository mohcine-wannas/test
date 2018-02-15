package ma.salamgaz.tawassol.security.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.security.model.SigningResponse;
import ma.salamgaz.tawassol.security.model.SigningResponseStatus;
import ma.salamgaz.tawassol.security.service.SigingService;

/**
 * 
 */
@Service
public class SigningServiceImpl implements SigingService {

    private static final Logger logger = LoggerFactory.getLogger(SigningServiceImpl.class);

    // @Value("${keystore.private.key.name}")
    private String privateKeyName;

    // @Value("${keystore.path}")
    private String keystorePath;

    // @Value("${keystore.password}")
    private String fileKeyPassword;

    // @Value("${keystore.kind}")
    private String keystoreKind;

    @Override
    public SigningResponse retrieveSignature() {
        SigningResponse response = new SigningResponse();
        response.setResponseStatus(SigningResponseStatus.OK);

        // Creation d'un KeyStore
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(keystoreKind);
        } catch (KeyStoreException keystoreExp) {
            logger.error(keystoreExp.getMessage(), keystoreExp);
            response.setResponseStatus(SigningResponseStatus.KO);
        }

        if (SigningResponseStatus.KO.name().equals(response.getResponseStatus().name())) {
            return response;
        }

        // Chargement du certificat
        try {
            ks.load(new FileInputStream(keystorePath), fileKeyPassword.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException exception) {
            logger.error(exception.getMessage(), exception);
            response.setResponseStatus(SigningResponseStatus.KO);
        }

        if (SigningResponseStatus.KO.name().equals(response.getResponseStatus().name())) {
            return response;
        }

        Enumeration<String> e = null;
        try {
            e = ks.aliases();
        } catch (KeyStoreException keystoreExp) {
            logger.error(keystoreExp.getMessage(), keystoreExp);
            response.setResponseStatus(SigningResponseStatus.KO);
        }

        if (SigningResponseStatus.KO.name().equals(response.getResponseStatus().name())) {
            return response;
        }

        // browse the aliases enum
        String alias = null;
        while (e.hasMoreElements()) {
            alias = e.nextElement();

            // break when find private key alias in the enum
            if (privateKeyName.equals(alias)) {
                break;
            }
        }

        // propertized private key alias not found
        if (alias == null) {
            response.setResponseStatus(SigningResponseStatus.KO);
            return response;
        }

        // retrieve the private key
        try {
            PrivateKey key = (PrivateKey) ks.getKey(alias, fileKeyPassword.toCharArray());
            response.setKey(key);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException keystoreExp) {
            logger.error(keystoreExp.getMessage(), keystoreExp);
            response.setResponseStatus(SigningResponseStatus.KO);
        }

        // private key not found in the keystore
        if (SigningResponseStatus.KO.name().equals(response.getResponseStatus().name())) {
            return response;
        }

        // retrieve certificats chain
        java.security.cert.Certificate[] chain = null;
        try {
            chain = ks.getCertificateChain(alias);
            response.setChain(chain);
        } catch (KeyStoreException keystoreExp) {
            logger.error(keystoreExp.getMessage(), keystoreExp);
            response.setResponseStatus(SigningResponseStatus.KO);
        }

        return response;
    }

}
