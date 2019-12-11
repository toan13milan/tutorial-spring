--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.22
-- Dumped by pg_dump version 9.4.22
-- Started on 2019-05-30 16:32:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 2120
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2125 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16400)
-- Name: app_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.app_role (
    role_id bigint NOT NULL,
    role_name character varying(30) NOT NULL
);


ALTER TABLE public.app_role OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16393)
-- Name: app_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.app_user (
    user_id bigint NOT NULL,
    user_name character varying(36) NOT NULL,
    encryted_password character varying(128) NOT NULL,
    enabled integer NOT NULL
);


ALTER TABLE public.app_user OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16748)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.cart (
    id integer NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16746)
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_id_seq OWNER TO postgres;

--
-- TOC entry 2126 (class 0 OID 0)
-- Dependencies: 180
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;


--
-- TOC entry 185 (class 1259 OID 16779)
-- Name: cart_productb; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.cart_productb (
    id bigint NOT NULL,
    cart_id bigint NOT NULL,
    amount bigint,
    productb_id bigint
);


ALTER TABLE public.cart_productb OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16777)
-- Name: cart_productb_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cart_productb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_productb_id_seq OWNER TO postgres;

--
-- TOC entry 2127 (class 0 OID 0)
-- Dependencies: 184
-- Name: cart_productb_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cart_productb_id_seq OWNED BY public.cart_productb.id;


--
-- TOC entry 193 (class 1259 OID 16870)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.categories (
    id bigint NOT NULL,
    name_cat character varying(255)
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16868)
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categories_id_seq OWNER TO postgres;

--
-- TOC entry 2128 (class 0 OID 0)
-- Dependencies: 192
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- TOC entry 191 (class 1259 OID 16866)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16807)
-- Name: history_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.history_user (
    id integer NOT NULL,
    action character varying(255),
    date date,
    product_name character varying(255),
    user_id bigint
);


ALTER TABLE public.history_user OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16805)
-- Name: history_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.history_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.history_user_id_seq OWNER TO postgres;

--
-- TOC entry 2129 (class 0 OID 0)
-- Dependencies: 186
-- Name: history_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.history_user_id_seq OWNED BY public.history_user.id;


--
-- TOC entry 190 (class 1259 OID 16864)
-- Name: my_seq_gen; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.my_seq_gen
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.my_seq_gen OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16850)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    name_user character varying(255),
    address character varying(255),
    type_pay bigint
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16848)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- TOC entry 2130 (class 0 OID 0)
-- Dependencies: 188
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 177 (class 1259 OID 16424)
-- Name: persistent_logins; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.persistent_logins (
    username character varying(64) NOT NULL,
    series character varying(64) NOT NULL,
    token character varying(64) NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16454)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name_product character varying(40),
    amount bigint,
    user_id bigint NOT NULL,
    pro_cost bigint,
    category_id bigint
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16452)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 2131 (class 0 OID 0)
-- Dependencies: 178
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 183 (class 1259 OID 16764)
-- Name: productb; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.productb (
    id integer NOT NULL,
    user_id integer NOT NULL,
    order_id bigint,
    name_product character varying(255),
    amount bigint,
    pro_cost bigint
);


ALTER TABLE public.productb OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16762)
-- Name: productb_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productb_id_seq OWNER TO postgres;

--
-- TOC entry 2132 (class 0 OID 0)
-- Dependencies: 182
-- Name: productb_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productb_id_seq OWNED BY public.productb.id;


--
-- TOC entry 176 (class 1259 OID 16407)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 1941 (class 2604 OID 16751)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 16782)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_productb ALTER COLUMN id SET DEFAULT nextval('public.cart_productb_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 16873)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 16810)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history_user ALTER COLUMN id SET DEFAULT nextval('public.history_user_id_seq'::regclass);


--
-- TOC entry 1945 (class 2604 OID 16853)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 16457)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 16767)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productb ALTER COLUMN id SET DEFAULT nextval('public.productb_id_seq'::regclass);


--
-- TOC entry 2096 (class 0 OID 16400)
-- Dependencies: 175
-- Data for Name: app_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.app_role (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.app_role (role_id, role_name) VALUES (2, 'ROLE_USER');


--
-- TOC entry 2095 (class 0 OID 16393)
-- Dependencies: 174
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.app_user (user_id, user_name, encryted_password, enabled) VALUES (2, 'dbuser1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
INSERT INTO public.app_user (user_id, user_name, encryted_password, enabled) VALUES (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);


--
-- TOC entry 2102 (class 0 OID 16748)
-- Dependencies: 181
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cart (id, user_id) VALUES (1, 1);
INSERT INTO public.cart (id, user_id) VALUES (2, 2);


--
-- TOC entry 2133 (class 0 OID 0)
-- Dependencies: 180
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_id_seq', 1, false);


--
-- TOC entry 2106 (class 0 OID 16779)
-- Dependencies: 185
-- Data for Name: cart_productb; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 184
-- Name: cart_productb_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cart_productb_id_seq', 14, true);


--
-- TOC entry 2114 (class 0 OID 16870)
-- Dependencies: 193
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categories (id, name_cat) VALUES (1, 'Electronic');
INSERT INTO public.categories (id, name_cat) VALUES (2, 'Clothes');
INSERT INTO public.categories (id, name_cat) VALUES (3, 'Book');


--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 192
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 3, true);


--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 191
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 29, true);


--
-- TOC entry 2108 (class 0 OID 16807)
-- Dependencies: 187
-- Data for Name: history_user; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 186
-- Name: history_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.history_user_id_seq', 34, true);


--
-- TOC entry 2138 (class 0 OID 0)
-- Dependencies: 190
-- Name: my_seq_gen; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.my_seq_gen', 3, true);


--
-- TOC entry 2110 (class 0 OID 16850)
-- Dependencies: 189
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (8, 'lam bui', 'phùng khoang', 2);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (11, 'lam bui', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (12, 'lam bui2', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (15, 'lam bui', 'phùng khoang', 2);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (18, 'lam bui', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (20, 'lam bui', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (22, 'lam bui2', 'phùng khoang', 2);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (24, 'lam bui', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (26, 'lam bui', 'phùng khoang', 1);
INSERT INTO public.orders (id, name_user, address, type_pay) VALUES (28, 'lam bui', 'phùng khoang', 1);


--
-- TOC entry 2139 (class 0 OID 0)
-- Dependencies: 188
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, true);


--
-- TOC entry 2098 (class 0 OID 16424)
-- Dependencies: 177
-- Data for Name: persistent_logins; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2100 (class 0 OID 16454)
-- Dependencies: 179
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (40, 'tuoi 18', 1, 1, 100000, 3);
INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (41, 'jean', 100, 1, 100, 2);
INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (42, 'short', 100, 1, 100, 2);
INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (39, 'man hinh LCD', 12, 1, 12, 1);
INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (16, 'abc', 123, 1, 1233, 2);
INSERT INTO public.product (id, name_product, amount, user_id, pro_cost, category_id) VALUES (38, 'may tinh', 100, 1, 100, 3);


--
-- TOC entry 2140 (class 0 OID 0)
-- Dependencies: 178
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 42, true);


--
-- TOC entry 2104 (class 0 OID 16764)
-- Dependencies: 183
-- Data for Name: productb; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2141 (class 0 OID 0)
-- Dependencies: 182
-- Name: productb_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productb_id_seq', 1, false);


--
-- TOC entry 2097 (class 0 OID 16407)
-- Dependencies: 176
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO public.user_role (id, user_id, role_id) VALUES (2, 1, 2);
INSERT INTO public.user_role (id, user_id, role_id) VALUES (3, 2, 2);


--
-- TOC entry 1952 (class 2606 OID 16404)
-- Name: app_role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.app_role
    ADD CONSTRAINT app_role_pk PRIMARY KEY (role_id);


--
-- TOC entry 1954 (class 2606 OID 16406)
-- Name: app_role_uk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.app_role
    ADD CONSTRAINT app_role_uk UNIQUE (role_name);


--
-- TOC entry 1948 (class 2606 OID 16397)
-- Name: app_user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pk PRIMARY KEY (user_id);


--
-- TOC entry 1950 (class 2606 OID 16399)
-- Name: app_user_uk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_uk UNIQUE (user_name);


--
-- TOC entry 1964 (class 2606 OID 16753)
-- Name: cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- TOC entry 1968 (class 2606 OID 16784)
-- Name: cart_productb_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.cart_productb
    ADD CONSTRAINT cart_productb_pk PRIMARY KEY (id);


--
-- TOC entry 1975 (class 2606 OID 16875)
-- Name: categories_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pk PRIMARY KEY (id);


--
-- TOC entry 1970 (class 2606 OID 16812)
-- Name: history_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.history_user
    ADD CONSTRAINT history_user_pkey PRIMARY KEY (id);


--
-- TOC entry 1973 (class 2606 OID 16858)
-- Name: order_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT order_pk PRIMARY KEY (id);


--
-- TOC entry 1960 (class 2606 OID 16428)
-- Name: persistent_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- TOC entry 1962 (class 2606 OID 16459)
-- Name: product_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- TOC entry 1966 (class 2606 OID 16769)
-- Name: productb_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.productb
    ADD CONSTRAINT productb_pkey PRIMARY KEY (id);


--
-- TOC entry 1956 (class 2606 OID 16411)
-- Name: user_role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pk PRIMARY KEY (id);


--
-- TOC entry 1958 (class 2606 OID 16413)
-- Name: user_role_uk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_uk UNIQUE (user_id, role_id);


--
-- TOC entry 1971 (class 1259 OID 16847)
-- Name: id_u; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE UNIQUE INDEX id_u ON public.history_user USING btree (id);


--
-- TOC entry 1984 (class 2606 OID 16841)
-- Name: cart_productb_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_productb
    ADD CONSTRAINT cart_productb_fk FOREIGN KEY (productb_id) REFERENCES public.product(id);


--
-- TOC entry 1983 (class 2606 OID 16787)
-- Name: cart_productb_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_productb
    ADD CONSTRAINT cart_productb_fk1 FOREIGN KEY (cart_id) REFERENCES public.cart(id);


--
-- TOC entry 1980 (class 2606 OID 16819)
-- Name: cart_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_user_fk FOREIGN KEY (user_id) REFERENCES public.app_user(user_id);


--
-- TOC entry 1979 (class 2606 OID 16876)
-- Name: category_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 1981 (class 2606 OID 16770)
-- Name: fkproductbou319971; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productb
    ADD CONSTRAINT fkproductbou319971 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id);


--
-- TOC entry 1985 (class 2606 OID 16836)
-- Name: history_user_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history_user
    ADD CONSTRAINT history_user_fk FOREIGN KEY (user_id) REFERENCES public.app_user(user_id);


--
-- TOC entry 1978 (class 2606 OID 16460)
-- Name: product_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_fk FOREIGN KEY (user_id) REFERENCES public.app_user(user_id);


--
-- TOC entry 1982 (class 2606 OID 16859)
-- Name: product_order_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productb
    ADD CONSTRAINT product_order_fk FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 1976 (class 2606 OID 16414)
-- Name: user_role_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_fk1 FOREIGN KEY (user_id) REFERENCES public.app_user(user_id);


--
-- TOC entry 1977 (class 2606 OID 16419)
-- Name: user_role_fk2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_fk2 FOREIGN KEY (role_id) REFERENCES public.app_role(role_id);


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-05-30 16:32:07

--
-- PostgreSQL database dump complete
--

