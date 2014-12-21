from flask import render_template, redirect, url_for, request, send_from_directory, flash
from setup import app
from models import PostHandler as PH
import os

@app.route('/')
def home():
	return render_template('index.html')

@app.route('/upload', methods=['POST'])
def upload():
	if 'secret_key' in request.form and request.form['secret_key'] == app.config['COMMUNICATION_KEY']:
		if 'date' in request.form and 'project_name' in request.form and 'screen_img' in request.files and 'camera_img' in request.files:
			date = request.form['date']
			project_name = request.form['project_name']
			screen_img = 'screen-' + project_name + '-' + date + '.png'
			camera_img = 'camera-' + project_name + '-' + date + '.png'
			request.files['screen_img'].save(os.path.join(app.config['UPLOADS_FOLDER'],screen_img))
			request.files['camera_img'].save(os.path.join(app.config['UPLOADS_FOLDER'],camera_img))
			PH().add_date(date,project_name,screen_img,camera_img)
			return 'MESSAGE RECEIVED'
		return 'BAD INPUTS'
	return 'CONNECTION DENIED'