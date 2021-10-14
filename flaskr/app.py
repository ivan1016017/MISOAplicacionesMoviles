from flaskr import create_app
from flask_restful import Api 
from flask_cors import CORS, cross_origin
from flask_jwt_extended import JWTManager


app = create_app('default')
app_context = app.app_context()
app_context.push()