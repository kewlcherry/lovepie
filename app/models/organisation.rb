class Organisation < ActiveRecord::Base
  has_many :mappings
  has_many :causes, :through => :mappings
  has_many :users, :through => :mappings
end
