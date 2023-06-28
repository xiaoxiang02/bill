package net.lzzy.accountingserver.constant;

/**
 * @author hp
 */
public class ApiConstant {

    private ApiConstant(){}

    public static final String API_PRODUCES = "application/json";

    /**
     * 以下为路由配置
     */
    public static final String ROUTE_EMPLOYEE_ROOT = "api/";
    public static final String ROUTE_EMPLOYEE_ALL = "/employee/";
    public static final String ROUTE_EMPLOYEE_REGISTER = "/register";
    public static final String ROUTE_EMPLOYEE_LOGIN = "/login";
    public static final String ROUTE_EMPLOYEE_LOGOUT = "/logout";
    public static final String ROUTE_EMPLOYEE_EXISTS = "/exists/{employee}";

    public static final String ROUTE_WORK_POSITION_ROOT = "api/workPosition";
    public static final String ROUTE_WORK_POSITION_ADD = "/add";
    public static final String ROUTE_WORK_POSITION_UPDATE = "/update";
    public static final String ROUTE_WORK_POSITION_REMOVE = "/remove";
    public static final String ROUTE_WORK_POSITION_EXISTS = "/exists/{workPosition}";

    public static final String ROUTE_BILL_ROOT = "api/bill";
    public static final String ROUTE_BILL_ALL = "/";
    public static final String ROUTE_BILL_ADD = "/add";
    public static final String ROUTE_BILL_UPDATE = "/update/{id}";
    public static final String ROUTE_BILL_DELETE = "/delete/{id}";

    public static final String ROUTE_TYPE_ROOT = "api/bill/type";
    public static final String ROUTE_TYPE_ALL = "/";
    public static final String ROUTE_TYPE_ADD = "/add";
    public static final String ROUTE_TYPE_EXISTS = "/exists/{type}";

    public static final String ROUTE_CATEGORY_ROOT = "api/bill/category";
    public static final String ROUTE_CATEGORY_ALL = "/";
    public static final String ROUTE_CATEGORY_ADD = "/add";
    public static final String ROUTE_CATEGORY_EXISTS = "/exists/{category}";



    /**
     * 以下为各接口的json key
     */
    public static final String KEY_LOGIN_EMPLOYEE_NUMBER = "employeeNumber";
    public static final String KEY_LOGIN_PASSWORD = "password";


    /**
     * 添加岗位的json key
     */
    public static final String KEY_WORK_POSITION_WORK_NAME = "workName";
    public static final String KEY_WORK_POSITION_DESCRIPTION = "description";


    /**
     * 添加员工的json key
     */
    public static final String KEY_EMPLOYEE_EMPLOYEE_NUMBER = "employee_number";
    public static final String KEY_EMPLOYEE_USER_NAME = "user_name";
    public static final String KEY_EMPLOYEE_PASSWORD = "password";
    public static final String KEY_EMPLOYEE_WORK_POSITION_ID = "work_position_id";
    public static final String KEY_EMPLOYEE_JOB_POSITION = "job_position";
    public static final String KEY_EMPLOYEE_GENDER = "gender";
    public static final String KEY_EMPLOYEE_EMAIL = "email";
    public static final String KEY_EMPLOYEE_PHONE = "phone";
    public static final String KEY_EMPLOYEE_IDENTITY_CARD_CODE = "identity_card_code";
    public static final String KEY_EMPLOYEE_BIRTH_DAY = "birth_day";
    public static final String KEY_EMPLOYEE_ADDRESS = "address";
    public static final String KEY_EMPLOYEE_AVATAR = "avatar";


    /**
     * 添加账单的json key
     */
    public static final String KEY_BILL_MONEY = "money";
    public static final String KEY_BILL_NOTES = "notes";
    public static final String KEY_BILL_TYPE_ID= "type_id";
    public static final String KEY_BILL_CATEGORY_ID= "category_id";

    /**
     * 添加账单类型的json key
     */
    public static final String KEY_TYPE_NAME = "type_name";

    /**
     * 添加账单用途类别的json key
     */
    public static final String KEY_CATEGORY_NAME = "category_name";


    /**
     * 删除用到的json key
     */
    public static final String KEY_BILL_ID = "id";
    public static final String KEY_TYPE_ID = "id";
    public static final String KEY_CATEGORY_ID = "id";

}
