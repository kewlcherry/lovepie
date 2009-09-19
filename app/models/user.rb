class User < ActiveRecord::Base
  acts_as_authentic
  
  has_many :mappings
  has_many :organisations, :through => :mappings
  has_many :causes, :through => :mappings
end
