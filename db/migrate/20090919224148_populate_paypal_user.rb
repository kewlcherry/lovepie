class PopulatePaypalUser < ActiveRecord::Migration
  def self.up
    User.new(:login => 'paypal', :password => 'test123', :password_confirmation => 'test123', :email => 'paypal@lovep.ie').save
    User.new(:login => 'paypal2', :password => 'test123', :password_confirmation => 'test123', :email => 'paypal2@lovep.ie').save
    User.new(:login => 'paypal3', :password => 'test123', :password_confirmation => 'test123', :email => 'paypal3@lovep.ie').save
  end

  def self.down
  end
end
