## Demo LTI 1.3 Tool in Spring boot

### 0. Generate JWKS

1. Generate JWT RS256 key and JWKS

```
ssh-keygen -t rsa -b 4096 -m PEM -f jwtRS256.key
-- Don't add passphrase
openssl rsa -in jwtRS256.key -pubout -outform PEM -out jwtRS256.key.pub
```

2. Convert public key to JWKS

Make a file called `public_key_to_jwks.py`

```
import json
from jwcrypto.jwk import JWK

f = open("jwtRS256.key.pub", "r")
public_key = f.read()
f.close()

jwk_obj = JWK.from_pem(public_key.encode('utf-8'))
public_jwk = json.loads(jwk_obj.export_public())
public_jwk['alg'] = 'RS256'
public_jwk['use'] = 'sig'
public_jwk_str = json.dumps(public_jwk)
print(public_jwk_str)
```

Run

```
pip3 install jwcrypto
python3 public_key_to_jwks.py
```

### 1. Create LTI Developer Key

Go to Admin > Site Admin > Developer Keys

Click `+ Developer Key` , choose LTI Key, then fill following 

* Key Name: Name of your tool's key (eg, Spring boot LTI 1.3 Tool)
* Owner email: your email
* Redirect URIs: https://tool-server/lti/login (eg, http://localhost:8080/lti/login)
* Target Link URL: your target url (eg, http://localhost:8080)
* OpenID Connect Initiation Url: http://localhost:8080/lti/login_initiation/canvas
* JWK Method: Paste JSON from `python3 public_key_to_jwks.py`
* Enable all LTI Advantage Services
* Setup Placements

Save

Turn On the created Developer Key, each key will has an id (10000000000005) and a secret - click `Show Key`

### 2. Setup Tool properties

Set up the following parameters in the `application.properties`

```
spring.security.oauth2.client.registration.canvas.client-id=10000000000005
spring.security.oauth2.client.registration.canvas.client-secret=
spring.security.oauth2.client.registration.canvas.authorization-grant-type=implicit
spring.security.oauth2.client.registration.canvas.scope=openid
spring.security.oauth2.client.registration.canvas.redirect-uri={baseUrl}/lti/login

spring.security.oauth2.client.provider.canvas.authorization-uri=http://localhost:3000/api/lti/authorize_redirect
spring.security.oauth2.client.provider.canvas.token-uri=http://localhost:3000/login/oauth2/token
spring.security.oauth2.client.provider.canvas.jwk-set-uri=http://localhost:3000/api/lti/security/jwks
spring.security.oauth2.client.provider.canvas.user-name-attribute=sub
```

* client-id: is the id of the Developer Key
* secret:  is the secret of the Developer Key
* authorization-uri: Canvas LMS authorization url
* token-uri: Canvas token url
* jwk-set-uri: Canvas jwk set url

### 3. Add your tool as an external app in Canvas

Go to Courses > {Course Name} > Settings > Apps

Click `+ App`

* Configuration Type: By Client ID
* Client ID: is the id of the Developer Key

Click `Install`

Refresh the course, you will see your tool appear in the course navigation
