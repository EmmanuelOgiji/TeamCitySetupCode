public class Resources {

    public static String returnRegExPattern(String request){
        String regex;
        if ("timestamp".equals(request)) {
            regex = "(?<timestamp>\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})";
        } else if ("token".equals(request)) {
            regex = "(?<token>\\d{19})";
        } else {
            throw new IllegalArgumentException("Invalid day of the week: " + request);
        }
        return regex;
    }

    public static  String postNewUser(){ return "/app/rest/users"; }

    public static  String postAdminUserBody(){
        return "{\n" +
                "  \"username\": \"Administrator\",\n" +
                "  \"name\": \"Admin\",\n" +
                "  \"id\": 0,\n" +
                "  \"email\": \"string\",\n" +
                "  \"lastLogin\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"hasPassword\": false,\n" +
                "  \"realm\": \"string\",\n" +
                "  \"href\": \"string\",\n" +
                "  \"properties\": {\n" +
                "    \"count\": 0,\n" +
                "    \"href\": \"/app/rest/users/id:0\",\n" +
                "    \"property\": [\n" +
                "      {\n" +
                "        \"name\": \"string\",\n" +
                "        \"value\": \"string\",\n" +
                "        \"inherited\": false,\n" +
                "        \"type\": {\n" +
                "          \"rawValue\": \"string\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"roles\": {\n" +
                "    \"role\": [\n" +
                "      {\n" +
                "        \"roleId\": \"SYSTEM_ADMIN\",\n" +
                "        \"scope\": \"g\",\n" +
                "        \"href\": \"/app/rest/users/id:0/roles/SYSTEM_ADMIN/g\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"groups\": {\n" +
                "    \"count\": 1,\n" +
                "    \"group\": [\n" +
                "      {\n" +
                "        \"key\": \"ALL_USERS_GROUP\",\n" +
                "        \"name\": \"All Users\",\n" +
                "        \"href\": \"/app/rest/userGroups/key:ALL_USERS_GROUP\",\n" +
                "        \"description\": \"Contains all TeamCity users\",\n" +
                "        \"users\": {\n" +
                "          \"count\": 0,\n" +
                "          \"user\": [\n" +
                "            null\n" +
                "          ]\n" +
                "        },\n" +
                "        \"roles\": {\n" +
                "          \"role\": [\n" +
                "            {\n" +
                "              \"roleId\": \"string\",\n" +
                "              \"scope\": \"string\",\n" +
                "              \"href\": \"string\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        \"properties\": {\n" +
                "          \"count\": 0,\n" +
                "          \"href\": \"string\",\n" +
                "          \"property\": [\n" +
                "            {\n" +
                "              \"name\": \"string\",\n" +
                "              \"value\": \"string\",\n" +
                "              \"inherited\": false,\n" +
                "              \"type\": {\n" +
                "                \"rawValue\": \"string\"\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"locator\": \"string\"\n" +
                "}";
    }
}
