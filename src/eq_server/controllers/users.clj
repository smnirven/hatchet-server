(ns eq-server.controllers.users
  (:require [eq-server.controllers :as controller]
            [eq-server.models.user :as user]
            [clojure.tools.logging :as log]
            [cheshire.core :refer :all]))

;; PARAMETER VALIDATIONS *************************************************************************************
(defn- validate-create-user-params!
  "Validates parameters required for creating a user"
  [params]
  (if-not (:email params) (throw (ex-info "email is a required parameter" {:response-code 400})))
  (if-not (:pwd params) (throw (ex-info "pwd is a required parameter" {:response-code 400})))
  (if-not (:pwd_conf params) (throw (ex-info "pwd_conf is a required parameter" {:response-code 400})))
  (if-not (= (:pwd params) (:pwd_conf params)) (throw (ex-info "passwords don't match" {:response-code 400})))
  (let [existing-user (user/find-user-by-email (:email params))]
    (if existing-user 
      (do 
        (log/debug (str "User " (:email params) " already exists"))
        (throw (ex-info (str "User " (:email params) " already exists") {:response-code 400}))))))

(defn- validate-authenticate-params!
  "Validates parameters required for authenticating a user"
  [params]
  (if-not (:email params) (throw (ex-info "email is a required parameter" {:response-code 400})))
  (if-not (:pwd params) (throw (ex-info "pwd is a required parameter" {:response-code 400})))
  (let [existing-user (user/find-user-by-email (:email params))]
    (if-not existing-user 
      (throw (ex-info (str "User " (:email params) " does not exist") {:response-code 401})))))

;; ************************************************************************************************************


(defn create-user
  "Creates a new user in the database"
  [request]
  (log/debug "Got a create user request")
  (let [params (:params request)]
    (validate-create-user-params! params)
    (let [user-guid (user/create! params)]
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:guid user-guid})})))

(defn authenticate
  "Authenticates a user with the specified email and password"
  [request]
  (let [params (:params request)]
    (validate-authenticate-params! params)
    (let [authenticated? (user/authenticate (:email params) (:pwd params))
          status (if-not authenticated? 401 200)]
      {:status status
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:authenticated authenticated?})})))