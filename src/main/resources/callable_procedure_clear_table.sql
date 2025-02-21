CREATE OR REPLACE PROCEDURE delete_user_by_first_name(firstname_input varchar)
LANGUAGE plpgsql
AS
$$
    BEGIN
            DELETE FROM users WHERE first_name = firstname_input;
    END;
$$;

--Вызываем процедуру--
--call delete_user_by_first_name('Bill222');