class Cause < ActiveRecord::Base

  validates_presence_of :name
  validates_uniqueness_of :name

  has_many :cause_organisations
  has_many :organisations, :through => :cause_organisations
end
