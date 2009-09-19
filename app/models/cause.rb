class Cause < ActiveRecord::Base
  has_many :mappings
  has_many :organisations, :through => :mappings
  has_many :users, :through => :mappings
end
