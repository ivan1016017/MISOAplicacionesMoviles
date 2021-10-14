from flask_restful import Resource 
from ..models  import db, User, UserSchema
from flask import request
from flask_jwt_extended import jwt_required, create_access_token

user_schema = UserSchema()