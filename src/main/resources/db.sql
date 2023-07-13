  INSERT INTO tb_usuario ( email, role, senha ,status,cpf)
 SELECT  'root@gmail.com','ADMIN','$2a$12$W4ga0wBDtSMXGY0y41/PAuY8o0E.SGucCpddVRY6gvZhPxA16bpGi','ATIVO','123456'
    WHERE NOT EXISTS (SELECT email FROM  tb_usuario WHERE id  = 1);


