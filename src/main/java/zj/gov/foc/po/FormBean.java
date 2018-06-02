package zj.gov.foc.po;

public class FormBean {
    /**
     * form : NUqDxa
     * form_name : 浙江省侨联信息登记表 V1.5
     * entry : {"serial_number":123,"field_1":"选项1","field_2":"张三","field_3":"这是一行文字","field_4":"这是一行文字","field_24":"这是一行文字","field_5":"张三","field_25":"张三","field_6":"选项1","field_7":"这是一行文字","field_8":"2018-06-02","field_26":"2018-06-02","field_9":"这是一行文字","field_53":"这是一行文字","field_10":"400-6606-892","field_27":"400-6606-892","field_11":"这是一行文字","field_28":"这是一行文字","field_12":"support@jinshuju.net","field_29":"support@jinshuju.net","field_13":123,"field_30":123,"field_56":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_57":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_15":"这是一行文字","field_32":"这是一行文字","field_16":"这是一行文字","field_33":"这是一行文字","field_17":"这是一行文字","field_34":"这是一行文字","field_18":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_35":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_43":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_44":{"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"},"field_19":"这是一行文字","field_36":"这是一行文字","field_20":"这是一行文字","field_37":"这是一行文字","field_21":"这是一行文字","field_38":"这是一行文字","field_22":"这是一行文字","field_39":"这是一行文字","field_40":"这是一行文字","field_41":"这是一行文字","field_42":"这是一行文字","field_54":"这是一行文字","field_55":"这是一行文字","field_45":"张三","field_46":"张三","field_47":"选项1","field_48":"选项1","field_49":"这是一行文字","field_50":"这是一行文字","field_51":"这是一行文字","field_52":"这是一行文字","field_23":"这是一行文字","creator_name":"小王","created_at":"2018-06-02 11:35:00 UTC","updated_at":"2018-06-02 11:35:00 UTC","info_remote_ip":"127.0.0.1"}
     */

    private String form;
    private String form_name;
    private EntryBean entry;

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public EntryBean getEntry() {
        return entry;
    }

    public void setEntry(EntryBean entry) {
        this.entry = entry;
    }

    public static class EntryBean {
        /**
         * serial_number : 123
         * field_1 : 选项1
         * field_2 : 张三
         * field_3 : 这是一行文字
         * field_4 : 这是一行文字
         * field_24 : 这是一行文字
         * field_5 : 张三
         * field_25 : 张三
         * field_6 : 选项1
         * field_7 : 这是一行文字
         * field_8 : 2018-06-02
         * field_26 : 2018-06-02
         * field_9 : 这是一行文字
         * field_53 : 这是一行文字
         * field_10 : 400-6606-892
         * field_27 : 400-6606-892
         * field_11 : 这是一行文字
         * field_28 : 这是一行文字
         * field_12 : support@jinshuju.net
         * field_29 : support@jinshuju.net
         * field_13 : 123
         * field_30 : 123
         * field_56 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_57 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_15 : 这是一行文字
         * field_32 : 这是一行文字
         * field_16 : 这是一行文字
         * field_33 : 这是一行文字
         * field_17 : 这是一行文字
         * field_34 : 这是一行文字
         * field_18 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_35 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_43 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_44 : {"province":"陕西省","city":"西安市","district":"雁塔区","street":"高新路"}
         * field_19 : 这是一行文字
         * field_36 : 这是一行文字
         * field_20 : 这是一行文字
         * field_37 : 这是一行文字
         * field_21 : 这是一行文字
         * field_38 : 这是一行文字
         * field_22 : 这是一行文字
         * field_39 : 这是一行文字
         * field_40 : 这是一行文字
         * field_41 : 这是一行文字
         * field_42 : 这是一行文字
         * field_54 : 这是一行文字
         * field_55 : 这是一行文字
         * field_45 : 张三
         * field_46 : 张三
         * field_47 : 选项1
         * field_48 : 选项1
         * field_49 : 这是一行文字
         * field_50 : 这是一行文字
         * field_51 : 这是一行文字
         * field_52 : 这是一行文字
         * field_23 : 这是一行文字
         * creator_name : 小王
         * created_at : 2018-06-02 11:35:00 UTC
         * updated_at : 2018-06-02 11:35:00 UTC
         * info_remote_ip : 127.0.0.1
         */

        private int serial_number;
        private String field_1;
        private String field_2;
        private String field_3;
        private String field_4;
        private String field_24;
        private String field_5;
        private String field_25;
        private String field_6;
        private String field_7;
        private String field_8;
        private String field_26;
        private String field_9;
        private String field_53;
        private String field_10;
        private String field_27;
        private String field_11;
        private String field_28;
        private String field_12;
        private String field_29;
        private int field_13;
        private int field_30;
        private Field56Bean field_56;
        private Field57Bean field_57;
        private String field_15;
        private String field_32;
        private String field_16;
        private String field_33;
        private String field_17;
        private String field_34;
        private Field18Bean field_18;
        private Field35Bean field_35;
        private Field43Bean field_43;
        private Field44Bean field_44;
        private String field_19;
        private String field_36;
        private String field_20;
        private String field_37;
        private String field_21;
        private String field_38;
        private String field_22;
        private String field_39;
        private String field_40;
        private String field_41;
        private String field_42;
        private String field_54;
        private String field_55;
        private String field_45;
        private String field_46;
        private String field_47;
        private String field_48;
        private String field_49;
        private String field_50;
        private String field_51;
        private String field_52;
        private String field_23;
        private String creator_name;
        private String created_at;
        private String updated_at;
        private String info_remote_ip;

        public int getSerial_number() {
            return serial_number;
        }

        public void setSerial_number(int serial_number) {
            this.serial_number = serial_number;
        }

        public String getField_1() {
            return field_1;
        }

        public void setField_1(String field_1) {
            this.field_1 = field_1;
        }

        public String getField_2() {
            return field_2;
        }

        public void setField_2(String field_2) {
            this.field_2 = field_2;
        }

        public String getField_3() {
            return field_3;
        }

        public void setField_3(String field_3) {
            this.field_3 = field_3;
        }

        public String getField_4() {
            return field_4;
        }

        public void setField_4(String field_4) {
            this.field_4 = field_4;
        }

        public String getField_24() {
            return field_24;
        }

        public void setField_24(String field_24) {
            this.field_24 = field_24;
        }

        public String getField_5() {
            return field_5;
        }

        public void setField_5(String field_5) {
            this.field_5 = field_5;
        }

        public String getField_25() {
            return field_25;
        }

        public void setField_25(String field_25) {
            this.field_25 = field_25;
        }

        public String getField_6() {
            return field_6;
        }

        public void setField_6(String field_6) {
            this.field_6 = field_6;
        }

        public String getField_7() {
            return field_7;
        }

        public void setField_7(String field_7) {
            this.field_7 = field_7;
        }

        public String getField_8() {
            return field_8;
        }

        public void setField_8(String field_8) {
            this.field_8 = field_8;
        }

        public String getField_26() {
            return field_26;
        }

        public void setField_26(String field_26) {
            this.field_26 = field_26;
        }

        public String getField_9() {
            return field_9;
        }

        public void setField_9(String field_9) {
            this.field_9 = field_9;
        }

        public String getField_53() {
            return field_53;
        }

        public void setField_53(String field_53) {
            this.field_53 = field_53;
        }

        public String getField_10() {
            return field_10;
        }

        public void setField_10(String field_10) {
            this.field_10 = field_10;
        }

        public String getField_27() {
            return field_27;
        }

        public void setField_27(String field_27) {
            this.field_27 = field_27;
        }

        public String getField_11() {
            return field_11;
        }

        public void setField_11(String field_11) {
            this.field_11 = field_11;
        }

        public String getField_28() {
            return field_28;
        }

        public void setField_28(String field_28) {
            this.field_28 = field_28;
        }

        public String getField_12() {
            return field_12;
        }

        public void setField_12(String field_12) {
            this.field_12 = field_12;
        }

        public String getField_29() {
            return field_29;
        }

        public void setField_29(String field_29) {
            this.field_29 = field_29;
        }

        public int getField_13() {
            return field_13;
        }

        public void setField_13(int field_13) {
            this.field_13 = field_13;
        }

        public int getField_30() {
            return field_30;
        }

        public void setField_30(int field_30) {
            this.field_30 = field_30;
        }

        public Field56Bean getField_56() {
            return field_56;
        }

        public void setField_56(Field56Bean field_56) {
            this.field_56 = field_56;
        }

        public Field57Bean getField_57() {
            return field_57;
        }

        public void setField_57(Field57Bean field_57) {
            this.field_57 = field_57;
        }

        public String getField_15() {
            return field_15;
        }

        public void setField_15(String field_15) {
            this.field_15 = field_15;
        }

        public String getField_32() {
            return field_32;
        }

        public void setField_32(String field_32) {
            this.field_32 = field_32;
        }

        public String getField_16() {
            return field_16;
        }

        public void setField_16(String field_16) {
            this.field_16 = field_16;
        }

        public String getField_33() {
            return field_33;
        }

        public void setField_33(String field_33) {
            this.field_33 = field_33;
        }

        public String getField_17() {
            return field_17;
        }

        public void setField_17(String field_17) {
            this.field_17 = field_17;
        }

        public String getField_34() {
            return field_34;
        }

        public void setField_34(String field_34) {
            this.field_34 = field_34;
        }

        public Field18Bean getField_18() {
            return field_18;
        }

        public void setField_18(Field18Bean field_18) {
            this.field_18 = field_18;
        }

        public Field35Bean getField_35() {
            return field_35;
        }

        public void setField_35(Field35Bean field_35) {
            this.field_35 = field_35;
        }

        public Field43Bean getField_43() {
            return field_43;
        }

        public void setField_43(Field43Bean field_43) {
            this.field_43 = field_43;
        }

        public Field44Bean getField_44() {
            return field_44;
        }

        public void setField_44(Field44Bean field_44) {
            this.field_44 = field_44;
        }

        public String getField_19() {
            return field_19;
        }

        public void setField_19(String field_19) {
            this.field_19 = field_19;
        }

        public String getField_36() {
            return field_36;
        }

        public void setField_36(String field_36) {
            this.field_36 = field_36;
        }

        public String getField_20() {
            return field_20;
        }

        public void setField_20(String field_20) {
            this.field_20 = field_20;
        }

        public String getField_37() {
            return field_37;
        }

        public void setField_37(String field_37) {
            this.field_37 = field_37;
        }

        public String getField_21() {
            return field_21;
        }

        public void setField_21(String field_21) {
            this.field_21 = field_21;
        }

        public String getField_38() {
            return field_38;
        }

        public void setField_38(String field_38) {
            this.field_38 = field_38;
        }

        public String getField_22() {
            return field_22;
        }

        public void setField_22(String field_22) {
            this.field_22 = field_22;
        }

        public String getField_39() {
            return field_39;
        }

        public void setField_39(String field_39) {
            this.field_39 = field_39;
        }

        public String getField_40() {
            return field_40;
        }

        public void setField_40(String field_40) {
            this.field_40 = field_40;
        }

        public String getField_41() {
            return field_41;
        }

        public void setField_41(String field_41) {
            this.field_41 = field_41;
        }

        public String getField_42() {
            return field_42;
        }

        public void setField_42(String field_42) {
            this.field_42 = field_42;
        }

        public String getField_54() {
            return field_54;
        }

        public void setField_54(String field_54) {
            this.field_54 = field_54;
        }

        public String getField_55() {
            return field_55;
        }

        public void setField_55(String field_55) {
            this.field_55 = field_55;
        }

        public String getField_45() {
            return field_45;
        }

        public void setField_45(String field_45) {
            this.field_45 = field_45;
        }

        public String getField_46() {
            return field_46;
        }

        public void setField_46(String field_46) {
            this.field_46 = field_46;
        }

        public String getField_47() {
            return field_47;
        }

        public void setField_47(String field_47) {
            this.field_47 = field_47;
        }

        public String getField_48() {
            return field_48;
        }

        public void setField_48(String field_48) {
            this.field_48 = field_48;
        }

        public String getField_49() {
            return field_49;
        }

        public void setField_49(String field_49) {
            this.field_49 = field_49;
        }

        public String getField_50() {
            return field_50;
        }

        public void setField_50(String field_50) {
            this.field_50 = field_50;
        }

        public String getField_51() {
            return field_51;
        }

        public void setField_51(String field_51) {
            this.field_51 = field_51;
        }

        public String getField_52() {
            return field_52;
        }

        public void setField_52(String field_52) {
            this.field_52 = field_52;
        }

        public String getField_23() {
            return field_23;
        }

        public void setField_23(String field_23) {
            this.field_23 = field_23;
        }

        public String getCreator_name() {
            return creator_name;
        }

        public void setCreator_name(String creator_name) {
            this.creator_name = creator_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getInfo_remote_ip() {
            return info_remote_ip;
        }

        public void setInfo_remote_ip(String info_remote_ip) {
            this.info_remote_ip = info_remote_ip;
        }

        public static class Field56Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }

        public static class Field57Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }

        public static class Field18Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }

        public static class Field35Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }

        public static class Field43Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }

        public static class Field44Bean {
            /**
             * province : 陕西省
             * city : 西安市
             * district : 雁塔区
             * street : 高新路
             */

            private String province;
            private String city;
            private String district;
            private String street;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }
        }
    }
}
