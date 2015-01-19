from flask import render_template, redirect, url_for, request, send_from_directory, flash
from setup import app, lm
from models import PostHandler as PH
from flask.ext.login import current_user, login_required, login_user, logout_user
import os

@app.route('/')
@login_required
def home():
	return render_template('index.html', recent=PH().get_recent())

@app.route('/login', methods=["GET", "POST"])
def login():
	if request.method == 'POST':
		if "username" in request.form and "password" in request.form:
			user = PH().verify_user(request.form['username'], request.form['password'])
			rem = "remember" in request.form
			if user:
				if login_user(user, remember=rem):
					current_user.auth_toggle()
					return redirect(url_for('home'))
			else:
				flash('Invalid username or password')
	return render_template('login.html')

@app.route('/logout')
def logout():
	current_user.auth_toggle()
	logout_user()
	return redirect(url_for('login'))

@lm.user_loader
def load_user(id):
	return PH().get_user(id)

@app.route('/upload', methods=['POST'])
def upload():
	print "[*] Request received"
	print "[*] Checking secret_key"
	if 'secret_key' in request.form and request.form['secret_key'] == app.config['COMMUNICATION_KEY']:
		print "[*] Checking date, project_name, screen_img, camera_img"
		if 'date' in request.form and 'project_name' in request.form and 'screen_img' in request.files and 'camera_img' in request.files:
			print "[*] Checks passed, saving images"
			date = request.form['date']
			project_name = request.form['project_name']
			screen_img = 'screen-' + project_name + '-' + date + '.png'
			camera_img = 'camera-' + project_name + '-' + date + '.png'
			request.files['screen_img'].save(os.path.join(app.config['UPLOADS_FOLDER'],screen_img))
			request.files['camera_img'].save(os.path.join(app.config['UPLOADS_FOLDER'],camera_img))
			PH().add_data(date,project_name,screen_img,camera_img)
			return 'MESSAGE RECEIVED'
		return 'BAD INPUTS'
	return 'CONNECTION DENIED'

@app.route('/view/<name>')
@login_required
def view(name):
	return send_from_directory(app.config['UPLOADS_FOLDER'], name)

@app.route('/search')
@login_required
def search():
	if 'q' in request.args:
		return render_template('search.html', results=PH().search_data(request.args['q']))
