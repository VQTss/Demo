package translate;

/**
 *
 * @author Thai Vo Quoc CE160568
 */
public class Translate {

    private String VietNamese;
    private String English;

    /**
     * Create new translate
     * @param VietNamese
     * @param English
     * @throws TranslateException
     */
    public Translate(String VietNamese, String English) throws TranslateException {
        this.setVietNamese(VietNamese);
        this.setEnglish(English);
    }

    /**
     * Gets the vietnamese of translate
     * @return
     */
    public String getVietNamese() {
        return VietNamese;
    }

    /**
     * Set the vietnamese of translate
     * @param VietNamese
     * @throws TranslateException
     */
    public void setVietNamese(String VietNamese) throws TranslateException {
        if (VietNamese.equals("")) {
            throw new TranslateException("Vietnamese can't be empty!");
        } else {
            this.VietNamese = VietNamese;
        }
    }

    /**
     * Gets the english of translate
     * @return
     */
    public String getEnglish() {
        return English;
    }

    /**
     * Set the english of translate
     * @param English
     * @throws TranslateException
     */
    public void setEnglish(String English) throws TranslateException {
        if (English.equals("")) {
            throw new TranslateException("English can't be empty!");
        } else {
            this.English = English;
        }
    }

}
