package com.goku.tmdb.data.entity.certifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCertifications {
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
        @SerializedName("US")
        private List<US> US;
        @SerializedName("AU")
        private List<AU> AU;
        @SerializedName("FI")
        private List<FI> FI;
        @SerializedName("PT")
        private List<PT> PT;
        @SerializedName("CA-QC")
        private List<CAQC> CAQC;
        @SerializedName("IT")
        private List<IT> IT;
        @SerializedName("CA")
        private List<CA> CA;
        @SerializedName("IN")
        private List<IN> IN;
        @SerializedName("SE")
        private List<SE> SE;
        @SerializedName("NO")
        private List<NO> NO;
        @SerializedName("DE")
        private List<DE> DE;
        @SerializedName("MY")
        private List<MY> MY;
        @SerializedName("PH")
        private List<PH> PH;
        @SerializedName("NL")
        private List<NL> NL;
        @SerializedName("BG")
        private List<BG> BG;
        @SerializedName("HU")
        private List<HU> HU;
        @SerializedName("NZ")
        private List<NZ> NZ;
        @SerializedName("DK")
        private List<DK> DK;
        @SerializedName("LT")
        private List<LT> LT;
        @SerializedName("GB")
        private List<GB> GB;
        @SerializedName("BR")
        private List<BR> BR;
        @SerializedName("RU")
        private List<RU> RU;
        @SerializedName("ES")
        private List<ES> ES;

        public List<FR> getFR() {
            return FR;
        }

        public void setFR(List<FR> FR) {
            this.FR = FR;
        }

        public List<US> getUS() {
            return US;
        }

        public void setUS(List<US> US) {
            this.US = US;
        }

        public List<AU> getAU() {
            return AU;
        }

        public void setAU(List<AU> AU) {
            this.AU = AU;
        }

        public List<FI> getFI() {
            return FI;
        }

        public void setFI(List<FI> FI) {
            this.FI = FI;
        }

        public List<PT> getPT() {
            return PT;
        }

        public void setPT(List<PT> PT) {
            this.PT = PT;
        }

        public List<CAQC> getCAQC() {
            return CAQC;
        }

        public void setCAQC(List<CAQC> CAQC) {
            this.CAQC = CAQC;
        }

        public List<IT> getIT() {
            return IT;
        }

        public void setIT(List<IT> IT) {
            this.IT = IT;
        }

        public List<CA> getCA() {
            return CA;
        }

        public void setCA(List<CA> CA) {
            this.CA = CA;
        }

        public List<IN> getIN() {
            return IN;
        }

        public void setIN(List<IN> IN) {
            this.IN = IN;
        }

        public List<SE> getSE() {
            return SE;
        }

        public void setSE(List<SE> SE) {
            this.SE = SE;
        }

        public List<NO> getNO() {
            return NO;
        }

        public void setNO(List<NO> NO) {
            this.NO = NO;
        }

        public List<DE> getDE() {
            return DE;
        }

        public void setDE(List<DE> DE) {
            this.DE = DE;
        }

        public List<MY> getMY() {
            return MY;
        }

        public void setMY(List<MY> MY) {
            this.MY = MY;
        }

        public List<PH> getPH() {
            return PH;
        }

        public void setPH(List<PH> PH) {
            this.PH = PH;
        }

        public List<NL> getNL() {
            return NL;
        }

        public void setNL(List<NL> NL) {
            this.NL = NL;
        }

        public List<BG> getBG() {
            return BG;
        }

        public void setBG(List<BG> BG) {
            this.BG = BG;
        }

        public List<HU> getHU() {
            return HU;
        }

        public void setHU(List<HU> HU) {
            this.HU = HU;
        }

        public List<NZ> getNZ() {
            return NZ;
        }

        public void setNZ(List<NZ> NZ) {
            this.NZ = NZ;
        }

        public List<DK> getDK() {
            return DK;
        }

        public void setDK(List<DK> DK) {
            this.DK = DK;
        }

        public List<LT> getLT() {
            return LT;
        }

        public void setLT(List<LT> LT) {
            this.LT = LT;
        }

        public List<GB> getGB() {
            return GB;
        }

        public void setGB(List<GB> GB) {
            this.GB = GB;
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

        public List<ES> getES() {
            return ES;
        }

        public void setES(List<ES> ES) {
            this.ES = ES;
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

        public static class FI {
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

        public static class IT {
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

        public static class IN {
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

        public static class SE {
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

        public static class NO {
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

        public static class MY {
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

        public static class BG {
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

        public static class NZ {
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

        public static class DK {
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
    }
}
