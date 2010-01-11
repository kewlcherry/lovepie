# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_lovepie_session',
  :secret      => '8e00c6cb35e34aef9b8cdb205dff300dff89ae7f7f786711f53b5d058c1ad9b687a9c583d8bf05b6995d145a74f07df81fa2bddfabaa81b90312a683f822d7d9'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store
