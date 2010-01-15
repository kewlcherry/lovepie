# This file is copied to ~/spec when you run 'ruby script/generate rspec'
# from the project root directory.
ENV["RAILS_ENV"] ||= 'test'
require File.expand_path(File.join(File.dirname(__FILE__), '..', 'config', 'environment'))
require 'spec/autorun'
require 'spec/rails'
require 'machinist'
require 'shoulda'
require 'sham'
require 'database_cleaner'

require File.join(File.dirname(__FILE__), 'blueprint.rb')

# Uncomment the next line to use webrat's matchers
#require 'webrat/integrations/rspec-rails'

Dir[File.expand_path(File.join(File.dirname(__FILE__), 'support', '**', '*.rb'))].each {|f| require f}

Spec::Runner.configure do |config|
  config.use_transactional_fixtures = false
  config.use_instantiated_fixtures = false
  config.fixture_path = RAILS_ROOT + '/spec/fixtures/'

  DatabaseCleaner.strategy = :transaction

  config.before(:all) { Sham.reset(:before_all) }
  config.before(:each) { Sham.reset(:before_each) }

  config.before(:suite) do
    DatabaseCleaner.strategy = :transaction
    DatabaseCleaner.clean_with(:truncation)
  end

  config.before(:each) do
    DatabaseCleaner.start
  end

  config.after(:each) do
    DatabaseCleaner.clean
  end
end
