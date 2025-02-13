CREATE FUNCTION find_password_by_login(login_input varchar)
    RETURNS varchar
    LANGUAGE plpgsql
AS
$$
DECLARE
    password_result varchar;
BEGIN
    SELECT password INTO password_result FROM security WHERE login = login_input;
    RETURN password_result;
END;
$$;

--Выполнение функции--
SELECT find_password_by_login('login_bill');