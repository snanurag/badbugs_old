package in.gs;

public class RequestClass {
    String firstName;
    String lastName;
    String age;
//    Params params;

//    public Params getParams() {
//        return params;
//    }
//
//    public void setParams(Params params) {
//        this.params = params;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RequestClass(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public RequestClass() {
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

//    class Params{
//        QueryString querystring;
//
//        public QueryString getQueryString() {
//            return querystring;
//        }
//
//        public void setQueryString(QueryString querystring) {
//            this.querystring = querystring;
//        }
//    }
//
//    class QueryString {
//        String age;
//
//        public void setAge(String age) {
//            this.age = age;
//        }
//
//        public String getAge() {
//            return age;
//        }
//    }
}
