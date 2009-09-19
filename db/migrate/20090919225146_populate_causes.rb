class PopulateCauses < ActiveRecord::Migration
  def self.up
    Cause.new(:name => 'Religious').save
    Cause.new(:name => 'Housing and Community Affairs').save
    Cause.new(:name => 'Civil Rights and Citizenship').save
    Cause.new(:name => 'Philanthropic Intermediation').save
    Cause.new(:name => 'International').save
    Cause.new(:name => 'Culture, Sport and Recreation').save
    Cause.new(:name => 'Education and Science').save
    Cause.new(:name => 'Health and Medicine').save
    Cause.new(:name => 'Business and Professional').save
    Cause.new(:name => 'Social Services and Relief').save
    Cause.new(:name => 'Environment and Protection of Animals').save
  end

  def self.down
    Cause.find(:first, :conditions => { :name => 'Religious' }).delete
    Cause.find(:first, :conditions => { :name => 'Housing and Community Affairs' }).delete
    Cause.find(:first, :conditions => { :name => 'Civil Rights and Citizenship' }).delete
    Cause.find(:first, :conditions => { :name => 'Philanthropic Intermediation' }).delete
    Cause.find(:first, :conditions => { :name => 'International' }).delete
    Cause.find(:first, :conditions => { :name => 'Culture, Sport and Recreation' }).delete
    Cause.find(:first, :conditions => { :name => 'Education and Science' }).delete
    Cause.find(:first, :conditions => { :name => 'Health and Medicine' }).delete
    Cause.find(:first, :conditions => { :name => 'Business and Professional' }).delete
    Cause.find(:first, :conditions => { :name => 'Social Services and Relief' }).delete
    Cause.find(:first, :conditions => { :name => 'Environment and Protection of Animals' }).delete
  end
end
