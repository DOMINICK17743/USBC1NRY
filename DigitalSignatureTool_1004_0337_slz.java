// 代码生成时间: 2025-10-04 03:37:23
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DigitalSignatureTool {

    /*
     * Generate a new key pair for digital signature operations.
     * 
     * @return A pair of PrivateKey and PublicKey.
     * @throws Exception If any error occurs during key pair generation.
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /*
     * Create a digital signature for a given data string using the private key.
     * 
     * @param data The data to be signed.
     * @param privateKey The private key for signing.
     * @return The Base64 encoded digital signature.
     * @throws Exception If any error occurs during signature creation.
     */
    public static String createSignature(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        byte[] signedData = signature.sign();
        return Base64.getEncoder().encodeToString(signedData);
    }

    /*
     * Verify a digital signature for a given data string using the public key.
     * 
     * @param data The data that was signed.
     * @param signature The digital signature to verify.
     * @param publicKey The public key for verification.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If any error occurs during signature verification.
     */
    public static boolean verifySignature(String data, String signature, PublicKey publicKey) throws Exception {
        Signature signatureVerification = Signature.getInstance("SHA256withRSA");
        signatureVerification.initVerify(publicKey);
        signatureVerification.update(data.getBytes());
        return signatureVerification.verify(Base64.getDecoder().decode(signature));
    }

    /*
     * Main method for demonstration purposes.
     * 
     * @param args Command line arguments.
     * @throws Exception If any error occurs.
     */
    public static void main(String[] args) throws Exception {
        // Generate a new key pair
        KeyPair keyPair = getKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Data to be signed
        String data = "Hello, this is a digital signature!";

        // Create a digital signature
        String signature = createSignature(data, privateKey);
        System.out.println("Digital Signature: " + signature);

        // Verify the digital signature
        boolean isValid = verifySignature(data, signature, publicKey);
        System.out.println("Is the digital signature valid? " + isValid);
    }
}