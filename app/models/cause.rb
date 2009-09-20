class Cause < ActiveRecord::Base
  has_many :mappings
  has_many :organisations, :through => :mappings
  has_many :users, :through => :mappings
  
  def self.find_by_mapp
    Cause.find(:all, 
      :select => 'causes.*, count(causes.id) AS count_all',
      :joins => :mappings, 
      :group => 'causes.id',
      :order => 'count_all DESC')
  end
end
