set :application, "lovepie"

default_run_options[:pty] = true
set :repository, "git@github.com:novoda/lovepie.git" 
set :scm, :git
set :user, "novoda"
set :scm_passphrase, "******" 

#set :deploy_via, :remote_cache
role :web, "lovep.ie" 
role :app, "lovep.ie" 
role :db, "lovep.ie", :primary => true

set :port, 22 

set :use_sudo, true

set :runner, "novoda"

set :deploy_to, "/var/www/#{application}"

#############################################################
# Passenger
#############################################################

namespace :passenger do
  desc "Restart Application"
  task :restart do
    run "touch #{current_path}/tmp/restart.txt"
  end
end

