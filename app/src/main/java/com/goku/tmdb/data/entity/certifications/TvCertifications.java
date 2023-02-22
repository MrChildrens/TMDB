package com.goku.tmdb.data.entity.certifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvCertifications {

    @SerializedName("certifications")
    private Certifications certifications;

    public Certifications getCertifications() {
        return certifications;
    }

    public void setCertifications(Certifications certifications) {
        this.certifications = certifications;
    }

    public static class Certifications {
        @SerializedName("FR")
        private List<FR> FR;
        @SerializedName("TH")
        private List<TH> TH;
        @SerializedName("GB")
        private List<GB> GB;
        @SerializedName("KR")
        private List<KR> KR;
        @SerializedName("CA-QC")
        private List<CAQC> CAQC;
        @SerializedName("HU")
        private List<HU> HU;
        @SerializedName("PH")
        private List<PH> PH;
        @SerializedName("US")
        private List<US> US;
        @SerializedName("NL")
        private List<NL> NL;
        @SerializedName("AU")
        private List<AU> AU;
        @SerializedName("BR")
        private List<BR> BR;
        @SerializedName("RU")
        private List<RU> RU;
        @SerializedName("DE")
        private List<DE> DE;
        @SerializedName("SK")
        private List<SK> SK;
        @SerializedName("PT")
        private List<PT> PT;
        @SerializedName("ES")
        private List<ES> ES;
        @SerializedName("CA")
        private List<CA> CA;
        @SerializedName("LT")
        private List<LT> LT;

        public List<FR> getFR() {
            return FR;
        }

        public void setFR(List<FR> FR) {
            this.FR = FR;
        }

        public List<TH> getTH() {
            return TH;
        }

        public void setTH(List<TH> TH) {
            this.TH = TH;
        }

        public List<GB> getGB() {
            return GB;
        }

        public void setGB(List<GB> GB) {
            this.GB = GB;
        }

        public List<KR> getKR() {
            return KR;
        }

        public void setKR(List<KR> KR) {
            this.KR = KR;
        }

        public List<CAQC> getCAQC() {
            return CAQC;
        }

        public void setCAQC(List<CAQC> CAQC) {
            this.CAQC = CAQC;
        }

        public List<HU> getHU() {
            return HU;
        }

        public void setHU(List<HU> HU) {
            this.HU = HU;
        }

        public List<PH> getPH() {
            return PH;
        }

        public void setPH(List<PH> PH) {
            this.PH = PH;
        }

        public List<US> getUS() {
            return US;
        }

        public void setUS(List<US> US) {
            this.US = US;
        }

        public List<NL> getNL() {
            return NL;
        }

        public void setNL(List<NL> NL) {
            this.NL = NL;
        }

        public List<AU> getAU() {
            return AU;
        }

        public void setAU(List<AU> AU) {
            this.AU = AU;
        }

        public List<BR> getBR() {
            return BR;
        }

        public void setBR(List<BR> BR) {
            this.BR = BR;
        }

        public List<RU> getRU() {
            return RU;
        }

        public void setRU(List<RU> RU) {
            this.RU = RU;
        }

        public List<DE> getDE() {
            return DE;
        }

        public void setDE(List<DE> DE) {
            this.DE = DE;
        }

        public List<SK> getSK() {
            return SK;
        }

        public void setSK(List<SK> SK) {
            this.SK = SK;
        }

        public List<PT> getPT() {
            return PT;
        }

        public void setPT(List<PT> PT) {
            this.PT = PT;
        }

        public List<ES> getES() {
            return ES;
        }

        public void setES(List<ES> ES) {
            this.ES = ES;
        }

        public List<CA> getCA() {
            return CA;
        }

        public void setCA(List<CA> CA) {
            this.CA = CA;
        }

        public List<LT> getLT() {
            return LT;
        }

        public void setLT(List<LT> LT) {
            this.LT = LT;
        }

        public static class FR {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class TH {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class GB {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class KR {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class CAQC {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class HU {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class PH {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class US {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class NL {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class AU {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class BR {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class RU {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class DE {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class SK {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class PT {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class ES {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class CA {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }

        public static class LT {
            @SerializedName("certification")
            private String certification;
            @SerializedName("meaning")
            private String meaning;
            @SerializedName("order")
            private Integer order;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getMeaning() {
                return meaning;
            }

            public void setMeaning(String meaning) {
                this.meaning = meaning;
            }

            public Integer getOrder() {
                return order;
            }

            public void setOrder(Integer order) {
                this.order = order;
            }
        }
    }
}
