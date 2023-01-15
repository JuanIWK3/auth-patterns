package com.example.demo.security;


import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

public class KeyUtils {
  @Value("access-token.private")
  private String accessTokenPrivateKeyPath;

  @Value("access-token.public")
  private String accessTokenPublicKeyPath;

  @Value("refresh-token.private")
  private String refreshTokenPrivateKeyPath;

  @Value("refresh-token.public")
  private String refreshTokenPublicKeyPath;


  private KeyPair _accessTokenKeyPair;
  private KeyPair _refreshTokenKeyPair;

  private KeyPair getAccessTokenKeyPair() {
    if (Objects.isNull(_accessTokenKeyPair)) {
      _accessTokenKeyPair = getKeyPair(accessTokenPublicKeyPath, accessTokenPrivateKeyPath);
    }

    return _accessTokenKeyPair;
  }

  private KeyPair getRefreshTokenKeyPair() {
    if (Objects.isNull(_refreshTokenKeyPair)) {
      _refreshTokenKeyPair = getKeyPair(refreshTokenPublicKeyPath, refreshTokenPrivateKeyPath);
    }

    return _refreshTokenKeyPair;
  }

  private KeyPair getKeyPair(String publicKeyPath, String privateKeyPath) {
    KeyPair keyPair;

    File publicKeyFile = new File(publicKeyPath);
    File privateKeyFile = new File(privateKeyPath);

    if (publicKeyFile.exists() && privateKeyFile.exists()) {
      try {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] publicKeyBytes = Files.readAllBytes((publicKeyFile.toPath()));
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        byte[] privateKeyBytes = Files.readAllBytes((publicKeyFile.toPath()));
        EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(publicKeySpec);

        keyPair = new KeyPair(publicKey, privateKey);
        return keyPair;
      } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      throw new RuntimeException("Public and private keys don't exist");
    }

  }

  public RSAPublicKey getAccessTokenPublicKey() {
    return (RSAPublicKey)  getAccessTokenKeyPair().getPublic();
  }

  public RSAPrivateKey getAccessTokenPrivateKey() {
    return (RSAPrivateKey)  getAccessTokenKeyPair().getPrivate();
  }

  public RSAPublicKey getRefreshTokenPublicKey() {
    return (RSAPublicKey)  getAccessTokenKeyPair().getPublic();
  }

  public RSAPublicKey getRefreshTokenPrivateKey() {
    return (RSAPublicKey)  getAccessTokenKeyPair().getPrivate();
  }


}
