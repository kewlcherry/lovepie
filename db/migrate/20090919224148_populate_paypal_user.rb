class PopulatePaypalUser < ActiveRecord::Migration
  def self.up
    User.new(:login => 'paypal', :password => 'test123', :password_confirmation => 'test123', :email => 'paypal@lovep.ie').save
  end

  def self.down
  end
end
